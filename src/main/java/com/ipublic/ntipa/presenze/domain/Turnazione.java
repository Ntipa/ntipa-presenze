package com.ipublic.ntipa.presenze.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Turnazione.
 */
@Entity
@Table(name = "T_TURNAZIONE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Turnazione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "turnazione")
    private String turnazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurnazione() {
        return turnazione;
    }

    public void setTurnazione(String turnazione) {
        this.turnazione = turnazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Turnazione turnazione = (Turnazione) o;

        if (id != null ? !id.equals(turnazione.id) : turnazione.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Turnazione{" +
                "id=" + id +
                ", turnazione='" + turnazione + "'" +
                '}';
    }
}
