package com.biblioteca.controller;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LivroRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class BibliotecaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private EmprestimoRepository emprestimoRepository;

    private List<Livro> livros;

    public void init() {
        this.livros = livroRepository.findAll();
    }

    /**
     * Retorna o nome do usuário logado
     */
    public String getUsuarioLogado() {
        HttpServletRequest request = 
            (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getUserPrincipal() != null ? 
            request.getUserPrincipal().getName() : "Anônimo";
    }

    /**
     * Verifica se o usuário é Admin
     */
    public boolean isAdmin() {
        HttpServletRequest request = 
            (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("ADMIN");
    }

    /**
     * Verifica se o usuário é Leitor (USER)
     */
    public boolean isLeitor() {
        HttpServletRequest request = 
            (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("USER");
    }

    /**
     * Faz logout do usuário
     */
    public String logout() {
        try {
            HttpServletRequest request = 
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/login.xhtml";
    }

    /**
     * Registra um empréstimo para o usuário logado
     */
    public String emprestarLivro(Long livroId) {
        Livro livro = livroRepository.findById(livroId);
        
        if (livro == null || !livro.getStatus().equals("Disponível")) {
            return null; // Livro não encontrado ou não disponível
        }

        // Criar empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setNomeUsuario(getUsuarioLogado());

        // Alterar status do livro
        livro.setStatus("Indisponível");
        livroRepository.update(livro);

        // Salvar empréstimo
        emprestimoRepository.save(emprestimo);

        return null; // Recarregar página
    }

    // Getters
    public List<Livro> getLivros() {
        if (livros == null) {
            init();
        }
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
