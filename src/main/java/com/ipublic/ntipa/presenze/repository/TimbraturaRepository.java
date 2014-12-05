package com.ipublic.ntipa.presenze.repository;

import com.ipublic.ntipa.presenze.domain.Timbratura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Timbratura entity.
 */
public interface TimbraturaRepository extends JpaRepository<Timbratura, Long> {

}
