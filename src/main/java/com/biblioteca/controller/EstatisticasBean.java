package com.biblioteca.controller;

import com.biblioteca.service.BibliotecaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/estatisticas")
@Produces(MediaType.APPLICATION_JSON)
public class EstatisticasBean {

    @Inject
    private BibliotecaService service;

    @GET
    public String obterEstatisticas() {
        try {
            return service.obterEstatisticasJson();
        } catch (Exception e) {
            return "{\"erro\": \"Erro ao buscar estatísticas\"}";
        }
    }
}
