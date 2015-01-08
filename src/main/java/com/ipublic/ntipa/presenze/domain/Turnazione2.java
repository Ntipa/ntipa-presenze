package com.ipublic.ntipa.presenze.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Turnazione2.
 */
@Entity
@Table(name = "T_TURNAZIONE2")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Turnazione2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "turnazione2")
    private String turnazione2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurnazione2() {
        return turnazione2;
    }

    public void setTurnazione2(String turnazione2) {
        this.turnazione2 = turnazione2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Turnazione2 turnazione2 = (Turnazione2) o;

        if (id != null ? !id.equals(turnazione2.id) : turnazione2.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Turnazione2{" +
                "id=" + id +
                ", turnazione2='" + turnazione2 + "'" +
                '}';
    }
}
