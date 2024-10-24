package com.egg.Persistence;

import com.egg.Entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClienteDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarCliente(Cliente cliente) throws Exception{
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarCliente (int idCliente)  throws Exception {
        return em.find(Cliente.class, idCliente);
    }

    public void actualizarCliente (Cliente newCliente) throws Exception {
        em.getTransaction().begin();
        em.merge(newCliente);
        em.getTransaction().commit();
    }

    public void removerCliente (int idCliente) throws Exception{
        Cliente cliente = em.find(Cliente.class, idCliente);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
    }
}
