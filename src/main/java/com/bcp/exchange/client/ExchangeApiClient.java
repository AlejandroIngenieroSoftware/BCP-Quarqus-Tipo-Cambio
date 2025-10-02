package com.bcp.exchange.client;

import com.bcp.exchange.dto.ExchangeResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/tipo-cambio")
@ClientHeaderParam(name = "User-Agent", value = "Quarkus-Exchange-Service/1.0")
@RegisterRestClient(configKey = "exchange-api")
public interface ExchangeApiClient {


    @GET
    @Path("/today.json")
    @Produces(MediaType.APPLICATION_JSON)
    ExchangeResponseDTO getTodayExchange();
}