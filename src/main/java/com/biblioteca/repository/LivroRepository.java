package com.biblioteca.repository;

import com.biblioteca.entity.Livro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class LivroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT l FROM Livro l LEFT JOIN FETCH l.autor", Livro.class).getResultList();
    }

    public Livro findById(int id) {
        return entityManager.createQuery("SELECT l FROM Livro l LEFT JOIN FETCH l.autor WHERE l.id = :id",Livro.class).setParameter("id", id).getSingleResult();
    }


    public long count() {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l", Long.class).getSingleResult();
    }

    public long countByDisponivel(boolean disponivel) {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l WHERE l.disponivel = :disponivel", Long.class)
            .setParameter("disponivel", disponivel)
            .getSingleResult();
    }

    public void salvar(Livro livro) {
        entityManager.persist(livro);
    }
}
