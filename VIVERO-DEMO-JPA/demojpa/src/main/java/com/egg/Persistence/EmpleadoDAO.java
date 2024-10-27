package com.egg.Persistence;

import java.util.List;

import com.egg.Entidades.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpleadoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void agregarEmpleado(Empleado empleado) throws Exception {
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    public void modificarEmpleado(Empleado empleado) throws Exception {
        em.getTransaction().begin();
        em.merge(empleado);
        em.getTransaction().commit();
    }

    public Empleado buscarEmpleado(int idEmpleado) {
        return em.find(Empleado.class, idEmpleado);
    }

    public void removerEmpleado(int idEmpleado) throws Exception {
        Empleado empleado = em.find(Empleado.class, idEmpleado);
        if (empleado != null) {
            em.getTransaction().begin();
            em.remove(empleado);
            em.getTransaction().commit();
        }
    }

    public List<Empleado> listarEmpleados() throws Exception {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    public List<Empleado> listarEmpleadosPorIdOficina (int idOficina) throws Exception {
        return em.createQuery("SELECT e FROM Empleado e WHERE e.oficina.idOficina = :idOficina", Empleado.class)
                .setParameter("idOficina",idOficina)
                .getResultList();
    }

    public List<Empleado> listarEmpleadosPorCodigoOficina(String codigo) throws Exception {
        return em.createQuery("SELECT e FROM Empleado e WHERE e.oficina.codigoOficina = :codigo", Empleado.class)
                .setParameter("codigo", codigo)
                .getResultList();
    }

    public List<Empleado> listarEmpleadosJefes() throws Exception {
        return em.createQuery("SELECT DISTINCT e FROM Empleado e WHERE EXISTS (SELECT 1 FROM Empleado sub WHERE sub.idJefe = e)", Empleado.class)
                .getResultList();
    }
}
