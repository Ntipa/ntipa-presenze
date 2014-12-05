package com.ipublic.ntipa.presenze.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ipublic.ntipa.presenze.domain.Timbratura;
import com.ipublic.ntipa.presenze.repository.TimbraturaRepository;
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
 * REST controller for managing Timbratura.
 */
@RestController
@RequestMapping("/app")
public class TimbraturaResource {

    private final Logger log = LoggerFactory.getLogger(TimbraturaResource.class);

    @Inject
    private TimbraturaRepository timbraturaRepository;

    /**
     * POST  /rest/timbraturas -> Create a new timbratura.
     */
    @RequestMapping(value = "/rest/timbraturas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Timbratura timbratura) {
        log.debug("REST request to save Timbratura : {}", timbratura);
        timbraturaRepository.save(timbratura);
    }

    /**
     * GET  /rest/timbraturas -> get all the timbraturas.
     */
    @RequestMapping(value = "/rest/timbraturas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Timbratura> getAll() {
        log.debug("REST request to get all Timbraturas");
        return timbraturaRepository.findAll();
    }

    /**
     * GET  /rest/timbraturas/:id -> get the "id" timbratura.
     */
    @RequestMapping(value = "/rest/timbraturas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Timbratura> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Timbratura : {}", id);
        Timbratura timbratura = timbraturaRepository.findOne(id);
        if (timbratura == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timbratura, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/timbraturas/:id -> delete the "id" timbratura.
     */
    @RequestMapping(value = "/rest/timbraturas/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Timbratura : {}", id);
        timbraturaRepository.delete(id);
    }
}
