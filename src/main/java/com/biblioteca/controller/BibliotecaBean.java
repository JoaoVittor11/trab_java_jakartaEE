package com.biblioteca.controller;

import com.biblioteca.entity.*;
import com.biblioteca.service.BibliotecaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("bibliotecaBean")
@ViewScoped
public class BibliotecaBean implements Serializable {

    @Inject
    private BibliotecaService service;

    private List<Autor> autores;
    private List<Livro> livros;
    private List<Emprestimo> emprestimosAtivos;

    private long totalLivros;
    private long livrosDisponiveis;
    private long emprestimosAtivosCount;
    private long totalAutores;

    // Campos para cadastro de autor
    private String novoAutorNome;
    private String novoAutorEmail;

    @PostConstruct
    public void init() {
        carregarDados();
        carregarEstatisticas();
    }

    public void carregarDados() {
        try {
            autores = service.listarTodosAutores();
            livros = service.listarTodosLivros();
            emprestimosAtivos = service.listarEmprestimosAtivos();
        } catch (Exception e) {
            // tratar erros
        }
    }

    public void carregarEstatisticas() {
        try {
            totalLivros = service.contarTotalLivros();
            livrosDisponiveis = service.contarLivrosDisponiveis();
            emprestimosAtivosCount = service.contarEmprestimosAtivos();
            totalAutores = service.contarTotalAutores();
        } catch (Exception e) {
            // tratar erros
        }
    }

    // Método para cadastrar autor
    public void cadastrarAutor() {
        try {
            service.cadastrarAutor(novoAutorNome, novoAutorEmail);
            novoAutorNome = null;
            novoAutorEmail = null;
            carregarDados();
            carregarEstatisticas();
        } catch (Exception e) {
            // tratar erro
        }
    }

    // Getters e setters padrão
    public List<Autor> getAutores() { return autores; }
    public List<Livro> getLivros() { return livros; }
    public List<Emprestimo> getEmprestimosAtivos() { return emprestimosAtivos; }
    public long getTotalLivros() { return totalLivros; }
    public long getLivrosDisponiveis() { return livrosDisponiveis; }
    public long getEmprestimosAtivosCount() { return emprestimosAtivosCount; }
    public long getTotalAutores() { return totalAutores; }
    public String getNovoAutorNome() { return novoAutorNome; }
    public void setNovoAutorNome(String novoAutorNome) { this.novoAutorNome = novoAutorNome; }
    public String getNovoAutorEmail() { return novoAutorEmail; }
    public void setNovoAutorEmail(String novoAutorEmail) { this.novoAutorEmail = novoAutorEmail; }

    public void recarregarDados() { init(); }
}
