package com.egg;

// import com.egg.Entidades.Cliente;
// import com.egg.Entidades.GamaProducto;
import com.egg.Servicios.ClienteServicio;
import com.egg.Servicios.GamaProductoServicio;
// import com.egg.Servicios.OficinaServicio;

public class Application {

    public static void main(String[] args) throws Exception {
        // Instanciar la clase OficinaServicio para acceder a sus métodos
        //OficinaServicio oficinaServicio = new OficinaServicio();
        // Llamar al método del servicio para crear una nueva oficina
        //oficinaServicio.crearOficna("Mendoza", "OFI01","CP5000", "Argentina", "Cuyo", "11111111");
    
        //Probando metodosServicios
        GamaProductoServicio gamaProductoServicio = new GamaProductoServicio();
        // gamaProductoServicio.guardarGamaProduct("descripcion", "descripcion vacio", "jabones", "null");
        // GamaProducto gp = gamaProductoServicio.buscarGamaProduct(6);
        // if (gp != null) {
        //     System.out.println(gp.toString());
        // }
        // gamaProductoServicio.modificarGamaProducto(6);
        gamaProductoServicio.removerGamaProducto(6);
        ClienteServicio clienteServicio = new ClienteServicio();
        // clienteServicio.guardarClient("Austin", "..", "748c8ce", "66554", "47855547", 14000, "Vane", "Araos", "Italia", "as", "+6658556944");
        // Cliente cl = clienteServicio.buscarClient(20);
        // if (cl != null) {
        //     System.out.println(cl.toString());
        // }
        // clienteServicio.modificarCliente(20);
        clienteServicio.removerCliente(20);
    }
}
