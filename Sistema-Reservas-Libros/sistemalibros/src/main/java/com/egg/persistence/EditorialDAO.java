package com.egg.persistence;

import com.egg.entidades.Editorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EditorialDAO {
    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardarEditorial (Editorial editorial ) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar la editorial: "+e.getMessage());
        } 
        em.close();
    }

    public void actualizarEditorial (Editorial editorial) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Editorial buscarEditorial (int id) {
        EntityManager em = emf.createEntityManager();
        Editorial editorial = em.find(Editorial.class, id);
        em.close();
        return editorial;
    }

    public Editorial buscarEditorialPorNombre (String nombre) {
        EntityManager em = emf.createEntityManager();
        Editorial editorial = em.find(Editorial.class, nombre);
        em.close();
        return editorial;
    }

    public void eliminarEditorial (int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Editorial editorial = em.find(Editorial.class, id);
            if (editorial != null) {
                em.remove(editorial);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existeEditorialPorNombre (String nombre) {
        return buscarEditorialPorNombre(nombre) != null;
    }
}
