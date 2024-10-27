package com.egg.Persistence;

import java.util.List;
import java.util.Date;
import com.egg.Entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PedidoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardaPedido(Pedido pedido) throws Exception {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public Pedido buscarPedido(int id) {
        return em.find(Pedido.class, id);
    }

    public void actualizarPedido(Pedido pedido) {
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }

    public void eliminarPedido(int id) {
        Pedido pedido = buscarPedido(id);
        if (pedido != null) {
            em.getTransaction().begin();
            em.remove(pedido);
            em.getTransaction().commit();
        }
    }

    public List<Pedido> listarPedidosFechaEntregaRetrasados() throws Exception {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.fechaEntrega > p.fechaEsperada ", Pedido.class)
                .getResultList();
    }

    public List<Pedido> listarPedidosClienteEntreFechas (String nombreCliente, Date fechaInicio, Date fechaFin) throws Exception {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.cliente.nombreCliente = :nombre AND p.fechaPedido BETWEEN :fechaInicial AND :fechaFinal", Pedido.class)
                .setParameter("nombre", nombreCliente)
                .setParameter("fechaInicial", fechaInicio)
                .setParameter("fechaFinal", fechaFin)
                .getResultList();
    }
}
