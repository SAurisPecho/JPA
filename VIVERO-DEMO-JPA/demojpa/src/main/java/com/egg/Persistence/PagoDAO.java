package com.egg.Persistence;

import com.egg.Entidades.Pago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PagoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarPago(Pago pago) {
        em.getTransaction().begin();
        em.persist(pago);
        em.getTransaction().commit();
    }

    public Pago buscarPago (int id) { 
        return em.find(Pago.class, id);
    }

    public void actualizarPago (Pago newPago) {
        em.getTransaction().begin();
        em.merge(newPago);
        em.getTransaction().commit();
    }

    public void eliminarPago (int id) throws Exception{
        Pago pago = buscarPago(id);
        if (pago != null) {
            em.getTransaction().begin();
            em.remove(pago);
            em.getTransaction().commit();
        }
    }
}
