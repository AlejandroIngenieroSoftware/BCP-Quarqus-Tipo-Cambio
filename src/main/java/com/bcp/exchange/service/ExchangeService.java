package com.bcp.exchange.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.bcp.exchange.dto.ExchangeResponseDTO;
import com.bcp.exchange.entity.ExchangeQueryEntity;
import com.bcp.exchange.client.ExchangeApiClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;

@ApplicationScoped
public class ExchangeService {

    private static final int LIMITE_CONSULTAS_DIARIAS = 10;

    @Inject
    @RestClient
    ExchangeApiClient exchangeApiClient;

    public boolean getExchange(String dni) {

        /*LocalDate hoy = LocalDate.now();
        long consultasHoy = ExchangeQueryEntity.count(
                "dni = ?1 AND fechaConsulta >= ?2 AND fechaConsulta < ?3",
                dni,
                java.sql.Date.valueOf(hoy),
                java.sql.Date.valueOf(hoy.plusDays(1))
        );*/

        LocalDate hoy = LocalDate.now();
        long querysToday = ExchangeQueryEntity.find(
                        "dni = ?1 AND function('date', getDate) = ?2",
                        dni, java.sql.Date.valueOf(hoy))
                .count();

        return querysToday < LIMITE_CONSULTAS_DIARIAS;
    }

    @Transactional
    public void saveExchangeRate(String dni) {
        ExchangeQueryEntity query = new ExchangeQueryEntity(dni);
        query.persist();
    }

    public ExchangeResponseDTO getExchangeRate() {
        return exchangeApiClient.getTodayExchange();

    }
}