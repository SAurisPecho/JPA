package com.egg.Servicios;

import com.egg.Entidades.Oficina;
import com.egg.Persistence.OficinaDAO;

public class OficinaServicio {
    private final OficinaDAO daoOficina;
    
    public OficinaServicio() {
        this.daoOficina = new OficinaDAO();
    }

    public void crearOficna( String ciudad, String codigodOficina, String codigoPostal, String pais, String region, String telefono) {
        try {
            // Crear una nueva instancia de Oficina
            Oficina oficinaNueva = new Oficina();

            oficinaNueva.setCiudad(ciudad);
            oficinaNueva.setCodigoOficina(codigodOficina);
            oficinaNueva.setCodigoPostal(codigoPostal);
            oficinaNueva.setPais(pais);
            oficinaNueva.setRegion(region);
            oficinaNueva.setTelefono(telefono);
            
            // Llamar al método de OficinaDAO para guardar la nueva oficina
            daoOficina.guardaOficina(oficinaNueva);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo la nueva oficina de manera correcta");
        }
    }

    public void modificarOficina(int id){
        try{
            Oficina newOficina = daoOficina.buscarOficina(id);
            if(newOficina != null) {
                newOficina.setCiudad("Bogota");
                daoOficina.actualizarOficina(newOficina);
                System.out.println("Oficina modificada correctamente (Ciudad)");
            }
        }catch (Exception e){
            System.out.println("Error al buscar o modificar la oficina: " + e.toString());
        }
    }
    
    public Oficina buscarOficina(int id){
        Oficina oficina = daoOficina.buscarOficina(id);
        try{
            if(oficina == null){
                System.out.println("No existe una oficina con el ID proporcionado");
            }
            return oficina;
        }catch(Exception e){
            System.out.println("Ocurrio un error buscando esta oficina" + e.toString());
        }
        return null;
    }
    
    public void eliminarOficina(int id){
        try{
            daoOficina.eliminarOficina(id);
        }catch(Exception e){
            System.out.println(e.toString() + "No se pudo eliminar la oficina de manera correcta");
        }
    }
}
