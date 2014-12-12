package com.ipublic.ntipa.presenze.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ModelloTurno.
 */
@Entity
@Table(name = "T_MODGIORNO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModelloGiorno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome")
	@NotNull
	@Enumerated(EnumType.STRING)
	private GiornoSettimana nome;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Periodo> periodi = new HashSet<Periodo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GiornoSettimana getNome() {
		return nome;
	}

	public void setNome(GiornoSettimana nome) {
		this.nome = nome;
	}

	public Set<Periodo> getPeriodi() {
		return periodi;
	}

	public void setPeriodi(Set<Periodo> periodi) {
		this.periodi = periodi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelloGiorno other = (ModelloGiorno) obj;
		if (nome != other.nome)
			return false;
		return true;
	}

}
