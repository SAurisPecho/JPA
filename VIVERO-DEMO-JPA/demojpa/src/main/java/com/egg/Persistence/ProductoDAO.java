package com.egg.Persistence;

import java.util.List;

import com.egg.Entidades.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardaProducto(Producto producto) throws Exception {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }

    public Producto buscarProducto(int id) {
        return em.find(Producto.class, id);
    }

    public void actualizarProducto(Producto producto) {
        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();
    }

    public void eliminarProducto(int id) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            em.getTransaction().begin();
            em.remove(producto);
            em.getTransaction().commit();
        }
    }

    public List<Producto> listaProductos() throws Exception{
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    public List<Producto> listarProductosPorDetalle(String nombreProducto) throws Exception {
        return em.createQuery("SELECT DISTINCT p FROM Producto p WHERE LOWER(p.nombreProducto) LIKE LOWER(:nombreProducto)",Producto.class)
                .setParameter("nombreProducto", "%"+nombreProducto+"%")
                .getResultList();
    }

    public List<Object[]> listar10ProductosMasVendidosEnPedidos() throws Exception {
        return em.createQuery("SELECT p.nombreProducto, SUM(dp.cantidad) as totalVendido FROM DetallePedido dp JOIN dp.producto p GROUP BY p.nombreProducto ORDER BY totalVendido DESC", Object[].class)
                .setMaxResults(10)
                .getResultList();
    }
}
