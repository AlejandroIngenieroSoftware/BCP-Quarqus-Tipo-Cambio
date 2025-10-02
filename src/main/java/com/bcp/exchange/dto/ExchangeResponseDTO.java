package com.bcp.exchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public class ExchangeResponseDTO {
    @JsonProperty("fecha")
    public String date;

    @JsonProperty("compra")
    public Double buy;

    @JsonProperty("venta")
    public Double sale;

}
