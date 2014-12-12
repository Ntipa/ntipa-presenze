package com.ipublic.ntipa.presenze.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ipublic.ntipa.presenze.domain.ModelloTurno;
import com.ipublic.ntipa.presenze.repository.ModelloTurnoRepository;
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
 * REST controller for managing ModelloTurno.
 */
@RestController
@RequestMapping("/app")
public class ModelloTurnoResource {

    private final Logger log = LoggerFactory.getLogger(ModelloTurnoResource.class);

    @Inject
    private ModelloTurnoRepository modelloTurnoRepository;

    /**
     * POST  /rest/modelloTurnos -> Create a new modelloTurno.
     */
    @RequestMapping(value = "/rest/modelloTurnos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody ModelloTurno modelloTurno) {
        log.debug("REST request to save ModelloTurno : {}", modelloTurno);
        modelloTurnoRepository.save(modelloTurno);
    }

    /**
     * GET  /rest/modelloTurnos -> get all the modelloTurnos.
     */
    @RequestMapping(value = "/rest/modelloTurnos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ModelloTurno> getAll() {
        log.debug("REST request to get all ModelloTurnos");
        return modelloTurnoRepository.findAll();
    }

    /**
     * GET  /rest/modelloTurnos/:id -> get the "id" modelloTurno.
     */
    @RequestMapping(value = "/rest/modelloTurnos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ModelloTurno> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ModelloTurno : {}", id);
        ModelloTurno modelloTurno = modelloTurnoRepository.findOne(id);
        if (modelloTurno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelloTurno, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/modelloTurnos/:id -> delete the "id" modelloTurno.
     */
    @RequestMapping(value = "/rest/modelloTurnos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ModelloTurno : {}", id);
        modelloTurnoRepository.delete(id);
    }
}
