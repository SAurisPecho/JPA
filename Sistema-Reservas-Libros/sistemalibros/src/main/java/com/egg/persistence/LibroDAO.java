package com.egg.persistence;

import java.util.List;

import com.egg.entidades.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class LibroDAO {
    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardarLibro (Libro libro) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar libro: "+e.getMessage());
        }
        em.close();
    }

    public void actualizarLibro (Libro libro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Libro buscarLibro (long isbn) {
        EntityManager em = emf.createEntityManager();
        Libro libro = em.find(Libro.class, isbn);
        em.close();
        return libro;
    }

    public List<Libro> buscarLibroPorNombre (String nombre) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :nombre", Libro.class)
                .setParameter("nombre", nombre).getResultList();
    }

    public void eliminarLibro (long isbn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, isbn);
            if (libro != null) {
                em.remove(libro);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existeLibroPorNombre (String nombre) {
        return buscarLibroPorNombre(nombre) != null;
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre = :nombreAutor", Libro.class)
                .setParameter("nombreAutor", nombreAutor)
                .getResultList();

    }

	public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombreEditorial", Libro.class)
                .setParameter("nombreEditorial", nombreEditorial)
                .getResultList();

    }

    
}
