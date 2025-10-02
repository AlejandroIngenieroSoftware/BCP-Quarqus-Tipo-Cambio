package com.bcp.exchange.service;

import com.bcp.exchange.repository.ExchangeQueryRepository;
import com.bcp.exchange.util.Constants;
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


    @Inject
    @RestClient
    ExchangeApiClient exchangeApiClient;

    @Inject
    ExchangeQueryRepository exchangeQueryRepository;

    public boolean getExchange(String dni) {

        LocalDate hoy = LocalDate.now();
        long querysToday = ExchangeQueryEntity.find(
                        "dni = ?1 AND function('date', getDate) = ?2",
                        dni, java.sql.Date.valueOf(hoy))
                .count();

        return querysToday < Constants.DAILY_CONSULTATION_LIMIT;
    }

    @Transactional
    public void saveExchangeRate(String dni) {
        ExchangeQueryEntity query = new ExchangeQueryEntity(dni);
        exchangeQueryRepository.saveExchangeRate(query);
    }

    public ExchangeResponseDTO getExchangeRate() {
        return exchangeApiClient.getTodayExchange();

    }
}