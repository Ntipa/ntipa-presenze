package com.ipublic.ntipa.presenze.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Table(name = "T_PERIODO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Periodo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Periodo [da=" + da + ", a=" + a + "]";
	}

}
