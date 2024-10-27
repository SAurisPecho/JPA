package com.egg.Persistence;

import java.util.List;

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

    public List<Cliente> listarClientes() throws Exception {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public List<Cliente> listarClientesPorNombre(String nombreABuscar) throws Exception {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.nombreContacto LIKE :nombre", Cliente.class)
                .setParameter("nombre", "%" + nombreABuscar + "%")
                .getResultList();
    }

    public List<Cliente> listarClientesCiudad (String ciudad) throws Exception {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.ciudad = :ciudad",Cliente.class)
                .setParameter("ciudad",ciudad)
                .getResultList();
    }

    public List<Cliente> listarClientesPorEmpleado(String nombreEmpleado) {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.idEmpleado.nombre = :nombreEmpleado", Cliente.class)
                .setParameter("nombreEmpleado", nombreEmpleado)
                .getResultList();
    }
}
