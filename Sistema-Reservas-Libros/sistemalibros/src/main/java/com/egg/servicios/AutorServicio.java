package com.egg.servicios;

import com.egg.entidades.Autor;
import com.egg.persistence.AutorDAO;

public class AutorServicio {
    private final AutorDAO autorDAO;

    public AutorServicio () {
        this.autorDAO = new AutorDAO();
    }

    public void crearAutor (String nombre, boolean alta) {
        try {
            validarAutor(nombre);
            validarNombreAutorUnico(nombre);
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setAlta(alta);

            autorDAO.guardarAutor(autor);
        } catch (Exception e) {
            System.out.println("Error al crear autor: "+ e.getMessage());
        }
    }

    private void validarAutor(String nombre) {
        if (nombre == null || nombre.isEmpty() ) {
            throw new IllegalArgumentException("Error el nombre del autor no puede estar vacio");
        }
    }

    private void validarNombreAutorUnico(String nombre) {
        if (autorDAO.existeAutorPorNombre(nombre)) {
            throw new IllegalArgumentException("Ya existe un autor con ese nombre.");
        }
    }

    public Autor buscarAutor (int id) {
        Autor autor = autorDAO.buscarAutor(id);
        try {
            if(autor == null) {
                System.out.println("No existe ningun autor con el ID: "+id);
            }
            return autor;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar el autor: "+e.getMessage());
        }
        return null;
    }

    public Autor buscarAutorNombre (String nombre) {
        Autor autor = autorDAO.buscarAutorPorNombre(nombre);
        try {
            if(autor == null) {
                System.out.println("No existe ningun autor con el nombre: "+nombre);
            }
            return autor;
        } catch (IllegalArgumentException e) {
            System.out.println("Ocurrio un error al buscar el autor: "+e.getMessage());
        }
        return null;
    }

    public void modificarAutor (int id, String nombre) {
        try {
            Autor newAutor = autorDAO.buscarAutor(id);
            if (newAutor != null) {
                newAutor.setNombre(nombre);
                autorDAO.actualizarAutor(newAutor);
                System.out.println("Autor modificado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o modificar el autor. "+e.getMessage());
        }
    }

    public void removerAutor (int id) {
        try {
            autorDAO.eliminarAutor(id);
        } catch (Exception e) {
            System.out.println(e.toString()+ " No se pudo remover el autor");
        }
    }

    public void darBajaAutor (int id) {
        try {
            Autor autor = autorDAO.buscarAutor(id);
            if (autor != null) {
                if (autor.getAlta()) {
                    autor.setAlta(false);
                    autorDAO.actualizarAutor(autor);
                    System.out.println("Autor dado de baja");
                } else {
                    System.out.println("El autor ya esta dado de baja en el sistema.");
                }
            } else {
                System.out.println("Autor no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al dar de baja al autor.");
        }
    }
    
}
