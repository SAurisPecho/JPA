package com.egg.Servicios;

import java.util.List;

import com.egg.Entidades.Empleado;
import com.egg.Entidades.Oficina;
import com.egg.Persistence.EmpleadoDAO;

public class EmpleadoServicio {
    private final EmpleadoDAO daoEmpleado;

    public EmpleadoServicio() {
        this.daoEmpleado = new EmpleadoDAO();
    }

    public void agregarEmpleado(String apellido, int codigoEmpleado, String email, String extension, 
                                String nombre, String puesto,Empleado idjefe,  Oficina oficina) {
        try {
            Empleado empleadoNuevo = new Empleado();
            empleadoNuevo.setApellido(apellido);
            empleadoNuevo.setCodigoEmpleado(codigoEmpleado);
            empleadoNuevo.setEmail(email);
            empleadoNuevo.setExtension(extension);
            empleadoNuevo.setNombre(nombre);
            empleadoNuevo.setPuesto(puesto);
            empleadoNuevo.setIdJefe(idjefe);
            empleadoNuevo.setOficina(oficina);
            daoEmpleado.agregarEmpleado(empleadoNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo agregar el empleado.");
        }
    }
    
    public void modificarEmpleado(int idEmpleado) {
        try {
            Empleado newEmpleado = daoEmpleado.buscarEmpleado(idEmpleado);
            if (newEmpleado != null) {
                newEmpleado.setEmail("nuevo@email.com");
                daoEmpleado.modificarEmpleado(newEmpleado);
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo modificar el empleado.");
        }
    }

    public Empleado buscarEmpleado(int idEmpleado) {
        Empleado empleado = daoEmpleado.buscarEmpleado(idEmpleado);
        try {
            if (empleado == null) {
                System.out.println("No existe un empleado con el ID proporcionado");
            }
            return empleado;
        } catch (Exception e) {
            System.out.println("Ocurrio un error buscando al empleado" + e.toString());
        }
        return null;
    }

    public void removerEmpleado(int idEmpleado) {
        try {
            daoEmpleado.removerEmpleado(idEmpleado);
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo remover el empleado.");
        }
    }

    public void listaEmpleados() throws Exception {
        List<Empleado> listaEmpleados = daoEmpleado.listarEmpleados();
        imprimirLista(listaEmpleados);
    }

    public void imprimirLista(List<Empleado> listaEmpleados) {
        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado.getIdEmpleado()+" - "+empleado.getNombre()+" - "+empleado.getEmail());
        }
    }

    public void listaEmpleadosPorOficinaId (int idOficina) throws Exception {
        List<Empleado> listaEmpleados = daoEmpleado.listarEmpleadosPorIdOficina(idOficina);
        System.out.println("Empleados de la oficina con ID " + idOficina + ":");
        imprimirLista(listaEmpleados);
    }

    public void listarEmpleadosPorCodigoOficina (String codigo) throws Exception {
        List<Empleado> listaEmpleados = daoEmpleado.listarEmpleadosPorCodigoOficina(codigo);
        System.out.println("Empleados de la oficina con codigo " + codigo + ":");
        imprimirLista(listaEmpleados);
    }

    public void listarEmpleadosJefes() throws Exception {
        List<Empleado> listaEmpleados = daoEmpleado.listarEmpleadosJefes();
        System.out.println("Empleados Jefes de la oficina: ");
        imprimirLista(listaEmpleados);
    }
}
