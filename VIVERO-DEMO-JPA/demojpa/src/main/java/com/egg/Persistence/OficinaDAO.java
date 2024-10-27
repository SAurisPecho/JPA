package com.egg.Persistence;

import java.util.List;

import com.egg.Entidades.Oficina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OficinaDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardaOficina(Oficina oficina) throws Exception {
        em.getTransaction().begin();
        em.persist(oficina);
        em.getTransaction().commit();
    }

    public void actualizarOficina(Oficina newOficina){
        em.getTransaction().begin();
        em.merge(newOficina);
        em.getTransaction().commit();
    }
    public Oficina buscarOficina(int id){
        return em.find(Oficina.class, id);
    }
    public void eliminarOficina(int id) throws Exception{
        Oficina oficina = buscarOficina(id);
        if (oficina != null) {
            em.getTransaction().begin();
            em.remove(oficina);
            em.getTransaction().commit();
        }
        
    }

    public List<Oficina> listarTodas() throws Exception {
        return em.createQuery("SELECT o FROM Oficina o", Oficina.class).getResultList();
    }
}
