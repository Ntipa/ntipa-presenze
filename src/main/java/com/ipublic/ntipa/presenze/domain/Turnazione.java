package com.ipublic.ntipa.presenze.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipublic.ntipa.presenze.domain.util.CustomDateTimeDeserializer;
import com.ipublic.ntipa.presenze.domain.util.CustomLocalDateTimeDeserializer;
import com.ipublic.ntipa.presenze.domain.util.CustomLocalDateTimeSerializer;

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

    
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime da;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime a;
	
	
	@ManyToOne
	@JoinColumn
	private ModelloTurno modelloTurno;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Periodo> periodi = new HashSet<Periodo>();
	
	
	@ElementCollection
	private Set<String> dipendenti = new HashSet<String>();
	
	
    public Set<String> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(Set<String> dipendenti) {
		this.dipendenti = dipendenti;
	}

	public LocalDateTime getDa() {
		return da;
	}

	public void setDa(LocalDateTime da) {
		this.da = da;
	}

	public LocalDateTime getA() {
		return a;
	}

	public void setA(LocalDateTime a) {
		this.a = a;
	}

	public ModelloTurno getModelloTurno() {
		return modelloTurno;
	}

	public void setModelloTurno(ModelloTurno modelloTurno) {
		this.modelloTurno = modelloTurno;
	}

	public Set<Periodo> getPeriodi() {
		return periodi;
	}

	public void setPeriodi(Set<Periodo> periodi) {
		this.periodi = periodi;
	}

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
