package com.ipublic.ntipa.presenze.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

import com.ipublic.ntipa.presenze.Application;
import com.ipublic.ntipa.presenze.domain.Timbratura;
import com.ipublic.ntipa.presenze.repository.TimbraturaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TimbraturaResource REST controller.
 *
 * @see TimbraturaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TimbraturaResourceTest {
   private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final String DEFAULT_USERNAME = "SAMPLE_TEXT";
    private static final String UPDATED_USERNAME = "UPDATED_TEXT";
    
   private static final DateTime DEFAULT_TIMBRATURA_DATE = new DateTime(0L);
   private static final DateTime UPDATED_TIMBRATURA_DATE = new DateTime().withMillisOfSecond(0);
   private static final String DEFAULT_TIMBRATURA_DATE_STR = dateTimeFormatter.print(DEFAULT_TIMBRATURA_DATE);
    
    private static final String DEFAULT_TIPOLOGIA = "SAMPLE_TEXT";
    private static final String UPDATED_TIPOLOGIA = "UPDATED_TEXT";
    
    private static final String DEFAULT_INFO = "SAMPLE_TEXT";
    private static final String UPDATED_INFO = "UPDATED_TEXT";
    

    @Inject
    private TimbraturaRepository timbraturaRepository;

    private MockMvc restTimbraturaMockMvc;

    private Timbratura timbratura;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TimbraturaResource timbraturaResource = new TimbraturaResource();
        ReflectionTestUtils.setField(timbraturaResource, "timbraturaRepository", timbraturaRepository);
        this.restTimbraturaMockMvc = MockMvcBuilders.standaloneSetup(timbraturaResource).build();
    }

    @Before
    public void initTest() {
        timbratura = new Timbratura();
        timbratura.setUsername(DEFAULT_USERNAME);
        timbratura.setTimbraturaDate(DEFAULT_TIMBRATURA_DATE);
        timbratura.setTipologia(DEFAULT_TIPOLOGIA);
        timbratura.setInfo(DEFAULT_INFO);
    }

    @Test
    @Transactional
    public void createTimbratura() throws Exception {
        // Validate the database is empty
        assertThat(timbraturaRepository.findAll()).hasSize(0);

        // Create the Timbratura
        restTimbraturaMockMvc.perform(post("/app/rest/timbraturas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(timbratura)))
                .andExpect(status().isOk());

        // Validate the Timbratura in the database
        List<Timbratura> timbraturas = timbraturaRepository.findAll();
        assertThat(timbraturas).hasSize(1);
        Timbratura testTimbratura = timbraturas.iterator().next();
        assertThat(testTimbratura.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testTimbratura.getTimbraturaDate()).isEqualTo(DEFAULT_TIMBRATURA_DATE);
        assertThat(testTimbratura.getTipologia()).isEqualTo(DEFAULT_TIPOLOGIA);
        assertThat(testTimbratura.getInfo()).isEqualTo(DEFAULT_INFO);
    }

    @Test
    @Transactional
    public void getAllTimbraturas() throws Exception {
        // Initialize the database
        timbraturaRepository.saveAndFlush(timbratura);

        // Get all the timbraturas
        restTimbraturaMockMvc.perform(get("/app/rest/timbraturas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(timbratura.getId().intValue()))
                .andExpect(jsonPath("$.[0].username").value(DEFAULT_USERNAME.toString()))
                .andExpect(jsonPath("$.[0].timbraturaDate").value(DEFAULT_TIMBRATURA_DATE_STR))
                .andExpect(jsonPath("$.[0].tipologia").value(DEFAULT_TIPOLOGIA.toString()))
                .andExpect(jsonPath("$.[0].info").value(DEFAULT_INFO.toString()));
    }

    @Test
    @Transactional
    public void getTimbratura() throws Exception {
        // Initialize the database
        timbraturaRepository.saveAndFlush(timbratura);

        // Get the timbratura
        restTimbraturaMockMvc.perform(get("/app/rest/timbraturas/{id}", timbratura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(timbratura.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.timbraturaDate").value(DEFAULT_TIMBRATURA_DATE_STR))
            .andExpect(jsonPath("$.tipologia").value(DEFAULT_TIPOLOGIA.toString()))
            .andExpect(jsonPath("$.info").value(DEFAULT_INFO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTimbratura() throws Exception {
        // Get the timbratura
        restTimbraturaMockMvc.perform(get("/app/rest/timbraturas/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTimbratura() throws Exception {
        // Initialize the database
        timbraturaRepository.saveAndFlush(timbratura);

        // Update the timbratura
        timbratura.setUsername(UPDATED_USERNAME);
        timbratura.setTimbraturaDate(UPDATED_TIMBRATURA_DATE);
        timbratura.setTipologia(UPDATED_TIPOLOGIA);
        timbratura.setInfo(UPDATED_INFO);
        restTimbraturaMockMvc.perform(post("/app/rest/timbraturas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(timbratura)))
                .andExpect(status().isOk());

        // Validate the Timbratura in the database
        List<Timbratura> timbraturas = timbraturaRepository.findAll();
        assertThat(timbraturas).hasSize(1);
        Timbratura testTimbratura = timbraturas.iterator().next();
        assertThat(testTimbratura.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testTimbratura.getTimbraturaDate()).isEqualTo(UPDATED_TIMBRATURA_DATE);
        assertThat(testTimbratura.getTipologia()).isEqualTo(UPDATED_TIPOLOGIA);
        assertThat(testTimbratura.getInfo()).isEqualTo(UPDATED_INFO);
    }

    @Test
    @Transactional
    public void deleteTimbratura() throws Exception {
        // Initialize the database
        timbraturaRepository.saveAndFlush(timbratura);

        // Get the timbratura
        restTimbraturaMockMvc.perform(delete("/app/rest/timbraturas/{id}", timbratura.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Timbratura> timbraturas = timbraturaRepository.findAll();
        assertThat(timbraturas).hasSize(0);
    }
}
