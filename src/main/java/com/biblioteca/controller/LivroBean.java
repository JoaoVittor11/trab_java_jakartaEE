package com.biblioteca.controller;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.AutorRepository;
import com.biblioteca.repository.LivroRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LivroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private AutorRepository autorRepository;

    private Livro livro = new Livro();
    private List<Livro> livros;
    private List<Autor> autores;

    public void init() {
        this.livro = new Livro();
        this.livros = livroRepository.findAll();
        this.autores = autorRepository.findAll();
    }

    /**
     * Cadastra um novo livro
     */
    public String salvarLivro() {
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty() ||
            livro.getAutor() == null) {
            return null; // Validação falhou
        }

        livro.setStatus("Disponível");
        livroRepository.save(livro);
        
        this.livro = new Livro();
        this.livros = livroRepository.findAll();

        return "redirect:/livros.xhtml";
    }

    /**
     * Busca livro por ID para edição
     */
    public void carregarLivro(Long id) {
        this.livro = livroRepository.findById(id);
    }

    /**
     * Atualiza um livro existente
     */
    public String atualizarLivro() {
        livroRepository.update(livro);
        this.livro = new Livro();
        this.livros = livroRepository.findAll();
        return "redirect:/livros.xhtml";
    }

    /**
     * Deleta um livro
     */
    public String deletarLivro(Long id) {
        livroRepository.delete(id);
        this.livros = livroRepository.findAll();
        return "redirect:/livros.xhtml";
    }

    // Getters e Setters
    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public List<Livro> getLivros() {
        if (livros == null) {
            init();
        }
        return livros;
    }
    public void setLivros(List<Livro> livros) { this.livros = livros; }

    public List<Autor> getAutores() {
        if (autores == null) {
            autores = autorRepository.findAll();
        }
        return autores;
    }
    public void setAutores(List<Autor> autores) { this.autores = autores; }
}
