package com.bcp.exchange.repository;

import com.bcp.exchange.entity.ExchangeQueryEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExchangeQueryRepository {

    public void saveExchangeRate(ExchangeQueryEntity query) {
        query.persist();
    }

    /*public long countQueriesToday(String dni) {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(),LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(),LocalTime.MAX);
        return ExchangeQueryEntity.count("dni=:dni and queryDate between :start and :end", Parameters.with("dni",dni).and("start",start).and("end",end));
     }*/
}