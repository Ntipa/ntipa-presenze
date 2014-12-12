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
import com.ipublic.ntipa.presenze.domain.ModelloTurno;
import com.ipublic.ntipa.presenze.repository.ModelloTurnoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ModelloTurnoResource REST controller.
 *
 * @see ModelloTurnoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ModelloTurnoResourceTest {

    private static final String DEFAULT_NOME = "SAMPLE_TEXT";
    private static final String UPDATED_NOME = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIZIONE = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIZIONE = "UPDATED_TEXT";
    

    @Inject
    private ModelloTurnoRepository modelloTurnoRepository;

    private MockMvc restModelloTurnoMockMvc;

    private ModelloTurno modelloTurno;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ModelloTurnoResource modelloTurnoResource = new ModelloTurnoResource();
        ReflectionTestUtils.setField(modelloTurnoResource, "modelloTurnoRepository", modelloTurnoRepository);
        this.restModelloTurnoMockMvc = MockMvcBuilders.standaloneSetup(modelloTurnoResource).build();
    }

    @Before
    public void initTest() {
        modelloTurno = new ModelloTurno();
        modelloTurno.setNome(DEFAULT_NOME);
        modelloTurno.setDescrizione(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void createModelloTurno() throws Exception {
        // Validate the database is empty
        assertThat(modelloTurnoRepository.findAll()).hasSize(0);

        // Create the ModelloTurno
        restModelloTurnoMockMvc.perform(post("/app/rest/modelloTurnos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(modelloTurno)))
                .andExpect(status().isOk());

        // Validate the ModelloTurno in the database
        List<ModelloTurno> modelloTurnos = modelloTurnoRepository.findAll();
        assertThat(modelloTurnos).hasSize(1);
        ModelloTurno testModelloTurno = modelloTurnos.iterator().next();
        assertThat(testModelloTurno.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testModelloTurno.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void getAllModelloTurnos() throws Exception {
        // Initialize the database
        modelloTurnoRepository.saveAndFlush(modelloTurno);

        // Get all the modelloTurnos
        restModelloTurnoMockMvc.perform(get("/app/rest/modelloTurnos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(modelloTurno.getId().intValue()))
                .andExpect(jsonPath("$.[0].nome").value(DEFAULT_NOME.toString()))
                .andExpect(jsonPath("$.[0].descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getModelloTurno() throws Exception {
        // Initialize the database
        modelloTurnoRepository.saveAndFlush(modelloTurno);

        // Get the modelloTurno
        restModelloTurnoMockMvc.perform(get("/app/rest/modelloTurnos/{id}", modelloTurno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(modelloTurno.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingModelloTurno() throws Exception {
        // Get the modelloTurno
        restModelloTurnoMockMvc.perform(get("/app/rest/modelloTurnos/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModelloTurno() throws Exception {
        // Initialize the database
        modelloTurnoRepository.saveAndFlush(modelloTurno);

        // Update the modelloTurno
        modelloTurno.setNome(UPDATED_NOME);
        modelloTurno.setDescrizione(UPDATED_DESCRIZIONE);
        restModelloTurnoMockMvc.perform(post("/app/rest/modelloTurnos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(modelloTurno)))
                .andExpect(status().isOk());

        // Validate the ModelloTurno in the database
        List<ModelloTurno> modelloTurnos = modelloTurnoRepository.findAll();
        assertThat(modelloTurnos).hasSize(1);
        ModelloTurno testModelloTurno = modelloTurnos.iterator().next();
        assertThat(testModelloTurno.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testModelloTurno.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    public void deleteModelloTurno() throws Exception {
        // Initialize the database
        modelloTurnoRepository.saveAndFlush(modelloTurno);

        // Get the modelloTurno
        restModelloTurnoMockMvc.perform(delete("/app/rest/modelloTurnos/{id}", modelloTurno.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ModelloTurno> modelloTurnos = modelloTurnoRepository.findAll();
        assertThat(modelloTurnos).hasSize(0);
    }
}
