package com.egg.Servicios;

import com.egg.Entidades.GamaProducto;
import com.egg.Persistence.GamaProductoDAO;

public class GamaProductoServicio {
    private final GamaProductoDAO daoGamaProducto;

    public GamaProductoServicio() {
        this.daoGamaProducto = new GamaProductoDAO();
    }

    public void guardarGamaProduct (String descripcionHTML, String descripcionTexto, String gama, String imagen) {
        try {
            GamaProducto gamaProductoNueva = new GamaProducto();

            gamaProductoNueva.setDescripcionHtml(descripcionHTML);
            gamaProductoNueva.setDescripcionTexto(descripcionTexto);
            gamaProductoNueva.setGama(gama);
            gamaProductoNueva.setImagen(imagen);

            daoGamaProducto.guardarGamaProducto(gamaProductoNueva);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo la nueva gama de producto de manera correcta");
        }
    }

    public GamaProducto buscarGamaProduct (int id) {
        GamaProducto gamaProducto = daoGamaProducto.buscarGamaProducto(id);
        try {
            if (gamaProducto == null) {
                System.out.println("No existe una gama de producto con el ID proporcionado");
            } 
            return gamaProducto;
        } catch (Exception e) {
            System.out.println("Ocurrio un error buscando esta gama de producto" + e.toString());
        }
        return null;
    }

    public void modificarGamaProducto (int id) {
        try {
            GamaProducto newGamaProducto = daoGamaProducto.buscarGamaProducto(id);
            if (newGamaProducto != null) {
                newGamaProducto.setDescripcionTexto("nueva");
                daoGamaProducto.actualizarGamaProducto(newGamaProducto);
                System.out.println("Gama de producto modificada correctamente (Descripcion Texto)");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o modificar la gama de producto: " + e.toString());
        }
    }

    public void removerGamaProducto (int idGamaProducto) throws Exception {
        try {
            daoGamaProducto.eliminarGamaProducto(idGamaProducto);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se pudo eliminar la gama de producto de manera correcta");
        }
    }
}
