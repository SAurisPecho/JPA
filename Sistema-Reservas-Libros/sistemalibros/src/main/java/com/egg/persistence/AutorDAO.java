package com.egg.persistence;

import com.egg.entidades.Autor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AutorDAO {
    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardarAutor(Autor autor) throws Exception {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar el autor: "+e.getMessage());
        }
        em.close();
    }

    public Autor buscarAutor(int id) {
        EntityManager em = emf.createEntityManager();
        Autor autor = em.find(Autor.class, id);
        em.close();
        return autor;
    }

    public Autor buscarAutorPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        Autor autor = em.find(Autor.class, nombre);
        return autor;
    }

    public void actualizarAutor (Autor autor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarAutor (int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Autor autor = em.find(Autor.class, id);
            if (autor != null) {
                em.remove(autor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existeAutorPorNombre(String nombre) {
        return buscarAutorPorNombre(nombre) != null;
    }
}
