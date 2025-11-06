package com.biblioteca.service;

import com.biblioteca.entity.*;
import com.biblioteca.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BibliotecaService {

    @Inject
    private AutorRepository autorRepository;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private EmprestimoRepository emprestimoRepository;

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(int id) {
        return livroRepository.findById(id);
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.findAtivos();
    }

    public long contarTotalLivros() {
        return livroRepository.count();
    }

    public long contarLivrosDisponiveis() {
        return livroRepository.countByDisponivel(true);
    }

    public long contarEmprestimosAtivos() {
        return emprestimoRepository.countAtivos();
    }

    public long contarTotalAutores() {
        return autorRepository.count();
    }

    public void cadastrarAutor(String nome, String email) {
        Autor autor = new Autor(nome, email);
        autorRepository.salvar(autor);
    }

    // Métodos para API REST
    public String obterEstatisticasJson() {
        try {
            long totalLivros = contarTotalLivros();
            long livrosDisponiveis = contarLivrosDisponiveis();
            long emprestimosAtivos = contarEmprestimosAtivos();
            long totalAutores = contarTotalAutores();

            return "{\"totalLivros\": " + totalLivros +
                   ", \"livrosDisponiveis\": " + livrosDisponiveis +
                   ", \"emprestimosAtivos\": " + emprestimosAtivos +
                   ", \"totalAutores\": " + totalAutores + "}";
        } catch (Exception e) {
            return "{\"erro\": \"Erro ao buscar estatísticas\"}";
        }
    }
}
