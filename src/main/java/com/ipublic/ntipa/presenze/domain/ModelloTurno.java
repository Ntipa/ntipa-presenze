package com.ipublic.ntipa.presenze.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A ModelloTurno.
 */
@Entity
@Table(name = "T_MODELTURNO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModelloTurno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;


    @OneToMany(cascade=CascadeType.ALL)
    private Set<ModelloRange> ranges = new HashSet<ModelloRange>();
    
//    @OneToMany(cascade=CascadeType.ALL)
//    private Map<GiornoSettimana,Set<Periodo>> ranges = new HashMap<GiornoSettimana, hashsset<Periodo>>();
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<ModelloRange> getRanges() {
		return ranges;
	}

	public void setRanges(Set<ModelloRange> ranges) {
		this.ranges = ranges;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModelloTurno modelloTurno = (ModelloTurno) o;

        if (id != null ? !id.equals(modelloTurno.id) : modelloTurno.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ModelloTurno{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                ", descrizione='" + descrizione + "'" +
                '}';
    }
}
