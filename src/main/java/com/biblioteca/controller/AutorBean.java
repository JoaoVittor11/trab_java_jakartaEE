package com.biblioteca.controller;

import com.biblioteca.entity.Autor;
import com.biblioteca.service.BibliotecaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/autores")
@Produces(MediaType.APPLICATION_JSON)
public class AutorBean {

    @Inject
    private BibliotecaService service;

    @GET
    public List<Autor> listarTodosAutores() {
        try {
            return service.listarTodosAutores();
        } catch (Exception e) {
            // tratar erros
            return null;
        }
    }
}
