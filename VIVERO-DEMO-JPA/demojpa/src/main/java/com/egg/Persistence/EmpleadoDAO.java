package com.egg.Persistence;

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
}
