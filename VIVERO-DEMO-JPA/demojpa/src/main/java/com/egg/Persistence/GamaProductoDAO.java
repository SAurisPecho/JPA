package com.egg.Persistence;

import java.util.List;

import com.egg.Entidades.GamaProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GamaProductoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarGamaProducto(GamaProducto gamaProducto) {
        em.getTransaction().begin();
        em.persist(gamaProducto);
        em.getTransaction().commit();
    }

    public GamaProducto buscarGamaProducto (int id) { 
        return em.find(GamaProducto.class, id);
    }

    public void actualizarGamaProducto (GamaProducto newGamaProducto) {
        em.getTransaction().begin();
        em.merge(newGamaProducto);
        em.getTransaction().commit();
    }

    public void eliminarGamaProducto (int id) throws Exception{
        GamaProducto gamaProducto = buscarGamaProducto(id);
        if (gamaProducto != null) {
            em.getTransaction().begin();
            em.remove(gamaProducto);
            em.getTransaction().commit();
        }
    }

    public List<GamaProducto> listaGamaProductos() throws Exception {
        return em.createQuery("SELECT g FROM GamaProducto g", GamaProducto.class).getResultList();
    }
}
