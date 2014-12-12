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
import java.util.List;

import com.ipublic.ntipa.presenze.Application;
import com.ipublic.ntipa.presenze.domain.Turnazione;
import com.ipublic.ntipa.presenze.repository.TurnazioneRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TurnazioneResource REST controller.
 *
 * @see TurnazioneResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TurnazioneResourceTest {

    private static final String DEFAULT_TURNAZIONE = "SAMPLE_TEXT";
    private static final String UPDATED_TURNAZIONE = "UPDATED_TEXT";
    

    @Inject
    private TurnazioneRepository turnazioneRepository;

    private MockMvc restTurnazioneMockMvc;

    private Turnazione turnazione;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TurnazioneResource turnazioneResource = new TurnazioneResource();
        ReflectionTestUtils.setField(turnazioneResource, "turnazioneRepository", turnazioneRepository);
        this.restTurnazioneMockMvc = MockMvcBuilders.standaloneSetup(turnazioneResource).build();
    }

    @Before
    public void initTest() {
        turnazione = new Turnazione();
        turnazione.setTurnazione(DEFAULT_TURNAZIONE);
    }

    @Test
    @Transactional
    public void createTurnazione() throws Exception {
        // Validate the database is empty
        assertThat(turnazioneRepository.findAll()).hasSize(0);

        // Create the Turnazione
        restTurnazioneMockMvc.perform(post("/app/rest/turnaziones")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(turnazione)))
                .andExpect(status().isOk());

        // Validate the Turnazione in the database
        List<Turnazione> turnaziones = turnazioneRepository.findAll();
        assertThat(turnaziones).hasSize(1);
        Turnazione testTurnazione = turnaziones.iterator().next();
        assertThat(testTurnazione.getTurnazione()).isEqualTo(DEFAULT_TURNAZIONE);
    }

    @Test
    @Transactional
    public void getAllTurnaziones() throws Exception {
        // Initialize the database
        turnazioneRepository.saveAndFlush(turnazione);

        // Get all the turnaziones
        restTurnazioneMockMvc.perform(get("/app/rest/turnaziones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(turnazione.getId().intValue()))
                .andExpect(jsonPath("$.[0].turnazione").value(DEFAULT_TURNAZIONE.toString()));
    }

    @Test
    @Transactional
    public void getTurnazione() throws Exception {
        // Initialize the database
        turnazioneRepository.saveAndFlush(turnazione);

        // Get the turnazione
        restTurnazioneMockMvc.perform(get("/app/rest/turnaziones/{id}", turnazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(turnazione.getId().intValue()))
            .andExpect(jsonPath("$.turnazione").value(DEFAULT_TURNAZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTurnazione() throws Exception {
        // Get the turnazione
        restTurnazioneMockMvc.perform(get("/app/rest/turnaziones/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTurnazione() throws Exception {
        // Initialize the database
        turnazioneRepository.saveAndFlush(turnazione);

        // Update the turnazione
        turnazione.setTurnazione(UPDATED_TURNAZIONE);
        restTurnazioneMockMvc.perform(post("/app/rest/turnaziones")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(turnazione)))
                .andExpect(status().isOk());

        // Validate the Turnazione in the database
        List<Turnazione> turnaziones = turnazioneRepository.findAll();
        assertThat(turnaziones).hasSize(1);
        Turnazione testTurnazione = turnaziones.iterator().next();
        assertThat(testTurnazione.getTurnazione()).isEqualTo(UPDATED_TURNAZIONE);
    }

    @Test
    @Transactional
    public void deleteTurnazione() throws Exception {
        // Initialize the database
        turnazioneRepository.saveAndFlush(turnazione);

        // Get the turnazione
        restTurnazioneMockMvc.perform(delete("/app/rest/turnaziones/{id}", turnazione.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Turnazione> turnaziones = turnazioneRepository.findAll();
        assertThat(turnaziones).hasSize(0);
    }
}
