package com.ipublic.ntipa.presenze.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ipublic.ntipa.presenze.domain.Turnazione2;
import com.ipublic.ntipa.presenze.repository.Turnazione2Repository;
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
 * REST controller for managing Turnazione2.
 */
@RestController
@RequestMapping("/app")
public class Turnazione2Resource {

    private final Logger log = LoggerFactory.getLogger(Turnazione2Resource.class);

    @Inject
    private Turnazione2Repository turnazione2Repository;

    /**
     * POST  /rest/turnazione2s -> Create a new turnazione2.
     */
    @RequestMapping(value = "/rest/turnazione2s",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Turnazione2 turnazione2) {
        log.debug("REST request to save Turnazione2 : {}", turnazione2);
        turnazione2Repository.save(turnazione2);
    }

    /**
     * GET  /rest/turnazione2s -> get all the turnazione2s.
     */
    @RequestMapping(value = "/rest/turnazione2s",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Turnazione2> getAll() {
        log.debug("REST request to get all Turnazione2s");
        return turnazione2Repository.findAll();
    }

    /**
     * GET  /rest/turnazione2s/:id -> get the "id" turnazione2.
     */
    @RequestMapping(value = "/rest/turnazione2s/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Turnazione2> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Turnazione2 : {}", id);
        Turnazione2 turnazione2 = turnazione2Repository.findOne(id);
        if (turnazione2 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(turnazione2, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/turnazione2s/:id -> delete the "id" turnazione2.
     */
    @RequestMapping(value = "/rest/turnazione2s/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Turnazione2 : {}", id);
        turnazione2Repository.delete(id);
    }
}
