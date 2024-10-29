package com.egg;

import java.util.List;
import java.util.Scanner;

import com.egg.entidades.Autor;
import com.egg.entidades.Libro;
import com.egg.servicios.AutorServicio;
import com.egg.servicios.LibroServicio;

public class Menu {
    private final LibroServicio ls = new LibroServicio();
    private final AutorServicio as = new AutorServicio();
    private final Scanner sc = new Scanner(System.in);
    private int opc = -1;

    public void ejecucion() {
        do {
            System.out.println("/// LIBRERIA ///");
            System.out.println("1. Búsqueda de un autor por nombre.");
            System.out.println("2. Búsqueda de un libro por ISBN." );
            System.out.println("3. Búsqueda de un libro por Título");
            System.out.println("4. Búsqueda de un libro/s por nombre de autor.");
            System.out.println("5. Búsqueda de un libro/s por nombre de Editorial.");
            System.out.println("0. SALIR");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    buscarAutorNombre();
                    break;
                case 2:
                    buscarLibroIsbn();
                    break;
                case 3:
                    buscarLibroTitulo();
                    break;
                case 4:
                    buscarLibrosAutor();
                    break;
                case 5:
                    buscarLibrosEditorial();
                    break;
                case 0:
                    System.out.println("SALIENDO");
                    break;
                default:
                    System.out.println("OPCIÓN INVÁLIDA");
            }
        } while (opc != 0);
    }

    private void buscarLibrosEditorial() {
        System.out.println("--- LIBRO ---");
        System.out.println("Ingrese la editorial: ");
        String editorialNombre = sc.nextLine();
        List<Libro> libros = ls.buscarLibroNombreAutor(editorialNombre);
        if (libros != null) {
            for (Libro l : libros) {
                System.out.println(l.toString());
            }
        }
    }

    private void buscarLibrosAutor() {
        System.out.println("--- LIBRO ---");
        System.out.println("Ingrese el autor: ");
        String autorNombre = sc.nextLine();
        List<Libro> libros = ls.buscarLibroNombreAutor(autorNombre);
        if (libros != null) {
            for (Libro l : libros) {
                System.out.println(l.toString());
            }
        }
    }

    private void buscarLibroTitulo() {
        System.out.println("--- LIBRO ---");
        System.out.println("Ingrese el titulo del libro: ");
        String titulo = sc.nextLine();
        List<Libro> libro = ls.buscarLibroTitulo(titulo);
        if (libro != null) {
            System.out.println(libro.toString());
        }
    }

    private void buscarLibroIsbn() {
        System.out.println("--- LIBRO ---");
        System.out.println("Ingrese el codigo isnb: ");
        long isnb = sc.nextLong();
        Libro libro = ls.buscarLibro(isnb);
        if (libro != null) {
            System.out.println(libro.toString());
        }
    }

    private void buscarAutorNombre() {
        System.out.println("--- AUTOR ---");
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = sc.nextLine();
        List<Autor> autor = as.buscarAutorNombre(nombre);
        if (autor != null) {
            System.out.println(autor.toString());
        }
    }
}
