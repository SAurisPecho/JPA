package com.egg.Persistence;

import com.egg.Entidades.DetallePedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DetallePedidoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarDetallePedido(DetallePedido detallePedido) {
        em.getTransaction().begin();
        em.persist(detallePedido);
        em.getTransaction().commit();
    }

    public DetallePedido buscarGamaProducto (int id) { 
        return em.find(DetallePedido.class, id);
    }

    public void actualizarDetallePedido (DetallePedido newDetallePedido) {
        em.getTransaction().begin();
        em.merge(newDetallePedido);
        em.getTransaction().commit();
    }

    public void eliminarDetallePedido (int id) throws Exception{
        DetallePedido detallePedido = buscarGamaProducto(id);
        if (detallePedido != null) {
            em.getTransaction().begin();
            em.remove(detallePedido);
            em.getTransaction().commit();
        }
    }
}
