package com.ipublic.ntipa.presenze.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ipublic.ntipa.presenze.domain.Turnazione;
import com.ipublic.ntipa.presenze.repository.TurnazioneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Turnazione.
 */
@RestController
@RequestMapping("/app")
public class TurnazioneResource {

    private final Logger log = LoggerFactory.getLogger(TurnazioneResource.class);

    @Inject
    private TurnazioneRepository turnazioneRepository;

    /**
     * POST  /rest/turnaziones -> Create a new turnazione.
     */
    @RequestMapping(value = "/rest/turnaziones",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Turnazione turnazione) {
        log.debug("REST request to save Turnazione : {}", turnazione);
        turnazioneRepository.save(turnazione);
    }

    /**
     * GET  /rest/turnaziones -> get all the turnaziones.
     */
    @RequestMapping(value = "/rest/turnaziones",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Turnazione> getAll() {
        log.debug("REST request to get all Turnaziones");
        return turnazioneRepository.findAll();
    }

    /**
     * GET  /rest/turnaziones/:id -> get the "id" turnazione.
     */
    @RequestMapping(value = "/rest/turnaziones/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Turnazione> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Turnazione : {}", id);
        Turnazione turnazione = turnazioneRepository.findOne(id);
        if (turnazione == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(turnazione, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/turnaziones/:id -> delete the "id" turnazione.
     */
    @RequestMapping(value = "/rest/turnaziones/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Turnazione : {}", id);
        turnazioneRepository.delete(id);
    }
}
