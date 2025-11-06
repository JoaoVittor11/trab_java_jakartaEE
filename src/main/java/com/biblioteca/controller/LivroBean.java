package com.biblioteca.controller;

import com.biblioteca.entity.Livro;
import com.biblioteca.service.BibliotecaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/livros")
@Produces(MediaType.APPLICATION_JSON)
public class LivroBean {

    @Inject
    private BibliotecaService service;

    @GET
    public List<Livro> listarTodosLivros() {
        try {
            return service.listarTodosLivros();
        } catch (Exception e) {
            // tratar erros
            return null;
        }
    }

    @GET
    @Path("/{id}")
    public Livro buscarLivroPorId(@PathParam("id") int id) {
        try {
            return service.buscarLivroPorId(id);
        } catch (Exception e) {
            // tratar erros
            return null;
        }
    }
}
