package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Autor;
import com.egg.entidades.Editorial;
import com.egg.entidades.Libro;
import com.egg.persistence.LibroDAO;
import java.lang.IllegalArgumentException;

public class LibroServicio {
    private final LibroDAO libroDAO;

    public LibroServicio () {
        this.libroDAO = new LibroDAO();
    }
    
    public void crearLibro (String titulo, int anio, int ejemplares, boolean alta, Autor autor, Editorial editorial) {
        try {
            validarLibro(titulo);
            validarNombreUnico(titulo);
            Libro newLibro = new Libro();
            newLibro.setTitulo(titulo);
            newLibro.setAnio(anio);
            newLibro.setEjemplares(ejemplares);
            newLibro.setAlta(alta);
            newLibro.setAutor(autor);
            newLibro.setEditorial(editorial);

            libroDAO.guardarLibro(newLibro);
        } catch (Exception e) {
            System.out.println("Error al crear libro: "+e.getMessage());
        }
    }

    private void validarLibro(String titulo) {
        if (titulo == null | titulo.isEmpty()) {
            throw new IllegalArgumentException("Error el titulo no puede estar vacio");
        }
    }

    private void validarNombreUnico(String titulo) {
        if (libroDAO.existeLibroPorNombre(titulo)) {
            throw new IllegalArgumentException("Ya existe un libro con ese nombre");
        }
    }

    public Libro buscarLibro (long isbn) {
        Libro libro = libroDAO.buscarLibro(isbn);
        try {
            if (libro == null) {
                System.out.println("No existe ningun Libro con el ID: "+isbn);
            }
            return libro;
        } catch (IllegalArgumentException e) {
            System.out.println("Ocurrio un error al buscar el Libro."+e.getMessage());
        }
        return null;
    }

    public List<Libro> buscarLibroTitulo (String titulo) {
        List<Libro> libro = libroDAO.buscarLibroPorNombre(titulo);
        try {
            if (libro == null) {
                System.out.println("No existe ningun Libro con el titulo: "+titulo);
            }
            return libro;
        } catch (IllegalArgumentException e) {
            System.out.println("Ocurrio un error al buscar el Libro."+e.getMessage());
        }
        return null;
    }

    public void modificarLibro(long isbn, String tituloNuevo) {
        try {
            Libro newLibro= libroDAO.buscarLibro(isbn);
            if (newLibro!= null) {
                newLibro.setTitulo(tituloNuevo);
                libroDAO.actualizarLibro(newLibro);
                System.out.println("Libro modificado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o modificar Libro: "+e.getMessage());
        }
    }

    public void removerLibro (long isbn) {
        try {
            libroDAO.eliminarLibro(isbn);
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo remover el Libro.");
        }
    }

    public void darBajaLibro (long isbn) {
        try {
            Libro libro = libroDAO.buscarLibro(isbn);
            if (libro != null) {
                if (libro.isAlta()) {
                    libro.setAlta(false);
                    libroDAO.actualizarLibro(libro);
                    System.out.println("Libro dado de baja");
                } else {
                    System.out.println("Libro ya está dado de baja en el sistema.");
                }
            } else {
                System.out.println("Libro no encontrado.");
            }    
        } catch (Exception e) {
            System.out.println("Error al dar de baja al Libro: "+e.getMessage());
        }
    }

    public List<Libro> buscarLibroNombreAutor (String nombreAutor) {
        return libroDAO.buscarLibrosPorAutor(nombreAutor);
    }

    public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        return libroDAO.buscarLibrosPorEditorial(nombreEditorial);
    }

}
