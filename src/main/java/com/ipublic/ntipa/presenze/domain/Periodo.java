package com.ipublic.ntipa.presenze.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipublic.ntipa.presenze.domain.util.CustomDateTimeDeserializer;
import com.ipublic.ntipa.presenze.domain.util.CustomLocalDateTimeDeserializer;
import com.ipublic.ntipa.presenze.domain.util.CustomLocalDateTimeSerializer;

@Embeddable
public class Periodo implements Serializable {

	public Periodo() {
	}

	public Periodo(LocalDateTime da, LocalDateTime a) {
		this.da = da;
		this.a = a;

	}

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

	@Override
	public String toString() {
		return "Periodo [da=" + da + ", a=" + a + "]";
	}

}
