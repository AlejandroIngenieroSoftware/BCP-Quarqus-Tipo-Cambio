package com.bcp.exchange.resource;

import com.bcp.exchange.dto.ExchangeResponseDTO;
import com.bcp.exchange.service.ExchangeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/api/exchange")
public class ExchangeResource {

    @Inject
    ExchangeService exchangeService;

    @GET
    public Response getExchangeService(@QueryParam("dni") String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"El parámetro 'dni' es obligatorio\"}")
                    .build();
        }

        if (!exchangeService.getExchange(dni)) {
            return Response.status(Response.Status.TOO_MANY_REQUESTS)
                    .entity("{\"error\": \"Límite de 10 consultas diarias alcanzado para este DNI\"}")
                    .build();
        }

        try {
            ExchangeResponseDTO response = exchangeService.getExchangeRate();
            exchangeService.saveExchangeRate(dni);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al obtener el tipo de cambio\"}")
                    .build();
        }
    }
}