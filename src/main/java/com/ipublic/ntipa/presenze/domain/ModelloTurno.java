package com.ipublic.ntipa.presenze.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

	@OneToMany(cascade = CascadeType.ALL)
	private Set<ModelloGiorno> giorni = new HashSet<ModelloGiorno>();

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

	public Set<ModelloGiorno> getGiorni() {
		return giorni;
	}

	public void setGiorni(Set<ModelloGiorno> giorni) {
		this.giorni = giorni;
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

		if (id != null ? !id.equals(modelloTurno.id) : modelloTurno.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "ModelloTurno{" + "id=" + id + ", nome='" + nome + "'"
				+ ", descrizione='" + descrizione + "'" + '}';
	}
}
