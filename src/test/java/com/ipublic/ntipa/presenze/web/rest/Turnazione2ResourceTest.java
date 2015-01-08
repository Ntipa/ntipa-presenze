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
import com.ipublic.ntipa.presenze.domain.Turnazione2;
import com.ipublic.ntipa.presenze.repository.Turnazione2Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Turnazione2Resource REST controller.
 *
 * @see Turnazione2Resource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class Turnazione2ResourceTest {

    private static final String DEFAULT_TURNAZIONE2 = "SAMPLE_TEXT";
    private static final String UPDATED_TURNAZIONE2 = "UPDATED_TEXT";
    

    @Inject
    private Turnazione2Repository turnazione2Repository;

    private MockMvc restTurnazione2MockMvc;

    private Turnazione2 turnazione2;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Turnazione2Resource turnazione2Resource = new Turnazione2Resource();
        ReflectionTestUtils.setField(turnazione2Resource, "turnazione2Repository", turnazione2Repository);
        this.restTurnazione2MockMvc = MockMvcBuilders.standaloneSetup(turnazione2Resource).build();
    }

    @Before
    public void initTest() {
        turnazione2 = new Turnazione2();
        turnazione2.setTurnazione2(DEFAULT_TURNAZIONE2);
    }

    @Test
    @Transactional
    public void createTurnazione2() throws Exception {
        // Validate the database is empty
        assertThat(turnazione2Repository.findAll()).hasSize(0);

        // Create the Turnazione2
        restTurnazione2MockMvc.perform(post("/app/rest/turnazione2s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(turnazione2)))
                .andExpect(status().isOk());

        // Validate the Turnazione2 in the database
        List<Turnazione2> turnazione2s = turnazione2Repository.findAll();
        assertThat(turnazione2s).hasSize(1);
        Turnazione2 testTurnazione2 = turnazione2s.iterator().next();
        assertThat(testTurnazione2.getTurnazione2()).isEqualTo(DEFAULT_TURNAZIONE2);
    }

    @Test
    @Transactional
    public void getAllTurnazione2s() throws Exception {
        // Initialize the database
        turnazione2Repository.saveAndFlush(turnazione2);

        // Get all the turnazione2s
        restTurnazione2MockMvc.perform(get("/app/rest/turnazione2s"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(turnazione2.getId().intValue()))
                .andExpect(jsonPath("$.[0].turnazione2").value(DEFAULT_TURNAZIONE2.toString()));
    }

    @Test
    @Transactional
    public void getTurnazione2() throws Exception {
        // Initialize the database
        turnazione2Repository.saveAndFlush(turnazione2);

        // Get the turnazione2
        restTurnazione2MockMvc.perform(get("/app/rest/turnazione2s/{id}", turnazione2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(turnazione2.getId().intValue()))
            .andExpect(jsonPath("$.turnazione2").value(DEFAULT_TURNAZIONE2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTurnazione2() throws Exception {
        // Get the turnazione2
        restTurnazione2MockMvc.perform(get("/app/rest/turnazione2s/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTurnazione2() throws Exception {
        // Initialize the database
        turnazione2Repository.saveAndFlush(turnazione2);

        // Update the turnazione2
        turnazione2.setTurnazione2(UPDATED_TURNAZIONE2);
        restTurnazione2MockMvc.perform(post("/app/rest/turnazione2s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(turnazione2)))
                .andExpect(status().isOk());

        // Validate the Turnazione2 in the database
        List<Turnazione2> turnazione2s = turnazione2Repository.findAll();
        assertThat(turnazione2s).hasSize(1);
        Turnazione2 testTurnazione2 = turnazione2s.iterator().next();
        assertThat(testTurnazione2.getTurnazione2()).isEqualTo(UPDATED_TURNAZIONE2);
    }

    @Test
    @Transactional
    public void deleteTurnazione2() throws Exception {
        // Initialize the database
        turnazione2Repository.saveAndFlush(turnazione2);

        // Get the turnazione2
        restTurnazione2MockMvc.perform(delete("/app/rest/turnazione2s/{id}", turnazione2.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Turnazione2> turnazione2s = turnazione2Repository.findAll();
        assertThat(turnazione2s).hasSize(0);
    }
}
