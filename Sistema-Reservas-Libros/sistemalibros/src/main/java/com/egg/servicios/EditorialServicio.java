package com.egg.servicios;

import com.egg.entidades.Editorial;
import com.egg.persistence.EditorialDAO;

public class EditorialServicio {
    private final EditorialDAO editorialDAO;

    public EditorialServicio () {
        this.editorialDAO = new EditorialDAO();
    }

    public void crearEditorial (String nombre, boolean alta) {
        try {
            validarEditorial(nombre);
            validarNombreUnico(nombre);
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(alta);

            editorialDAO.guardarEditorial(editorial);
        } catch (Exception e) {
            System.out.println("Error al crear editorial: "+e.getMessage());
        }
    }

    private void validarEditorial (String nombre) {
        if (nombre == null | nombre.isEmpty()) {
            throw new IllegalArgumentException("Error el nombre no puede estar vacio");
        } 
    }

    private void validarNombreUnico(String nombre) {
        if (editorialDAO.existeEditorialPorNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe una editorial con ese nombre");
        }
    }

    public Editorial buscarEditorial (int id) {
        Editorial editorial = editorialDAO.buscarEditorial(id);
        try {
            if (editorial == null) {
                System.out.println("No existe ninguna editorial con el ID: "+id);
            }
            return editorial;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar la editorial."+e.getMessage());
        }
        return null;
    }

    public void modificarEditorial (int id, String nombreNuevo) {
        try {
            Editorial newEditorial = editorialDAO.buscarEditorial(id);
            if (newEditorial != null) {
                newEditorial.setNombre(nombreNuevo);
                editorialDAO.actualizarEditorial(newEditorial);
                System.out.println("Editorial modificada correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o modificar editorial : "+e.getMessage());
        }
    }

    public void removerEditorial (int id) {
        try {
            editorialDAO.eliminarEditorial(id);
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo remover la editorial.");
        }
    }

    public void darBajaEditorial (int id) {
        try {
            Editorial editorial = editorialDAO.buscarEditorial(id);
            if (editorial != null) {
                if (editorial.getAlta()) {
                    editorial.setAlta(false);
                    editorialDAO.actualizarEditorial(editorial);
                    System.out.println("Editorial dada de baja");
                } else {
                    System.out.println("La editorial ya está dada de baja en el sistema.");
                }
            } else {
                System.out.println("Editorial no encontrada.");
            }    
        } catch (Exception e) {
            System.out.println("Error al dar de baja a la editorial: "+e.getMessage());
        }
    }
}
