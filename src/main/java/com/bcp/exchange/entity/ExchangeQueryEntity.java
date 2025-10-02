package com.bcp.exchange.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
@Entity
@Table(name="exchange_queries")
public class ExchangeQueryEntity extends PanacheEntity {

    public String dni;

    @Temporal(TemporalType.DATE)
    public Date getDate;

    // Constructor vacío necesario para JPA
    public ExchangeQueryEntity() {}

    public ExchangeQueryEntity(String dni) {
        this.dni = dni;
        this.getDate = new Date(); // Fecha actual (solo día)
    }
}