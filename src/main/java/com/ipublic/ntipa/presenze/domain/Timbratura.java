package com.ipublic.ntipa.presenze.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipublic.ntipa.presenze.domain.util.CustomDateTimeDeserializer;
import com.ipublic.ntipa.presenze.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Timbratura.
 */
@Entity
@Table(name = "T_TIMBRATURA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Timbratura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "timbratura_date", nullable = false)
    private DateTime timbraturaDate;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "info")
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DateTime getTimbraturaDate() {
        return timbraturaDate;
    }

    public void setTimbraturaDate(DateTime timbraturaDate) {
        this.timbraturaDate = timbraturaDate;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Timbratura timbratura = (Timbratura) o;

        if (id != null ? !id.equals(timbratura.id) : timbratura.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Timbratura{" +
                "id=" + id +
                ", username='" + username + "'" +
                ", timbraturaDate='" + timbraturaDate + "'" +
                ", tipologia='" + tipologia + "'" +
                ", info='" + info + "'" +
                '}';
    }
}
