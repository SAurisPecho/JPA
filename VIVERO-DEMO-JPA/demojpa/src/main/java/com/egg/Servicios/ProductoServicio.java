package com.egg.Servicios;

import java.util.List;

import com.egg.Entidades.GamaProducto;
import com.egg.Entidades.Producto;
import com.egg.Persistence.ProductoDAO;

public class ProductoServicio {
     private final ProductoDAO daoProducto;
    
    public ProductoServicio() {
        this.daoProducto = new ProductoDAO();
    }

    public void crearProducto( short stock, String codigoProd, String descripcion, String dimensione, String nombre, double precioProveedor, double precioVenta, String proveedor, GamaProducto gamaProducto ) {
        try {
            Producto productoNuevo = new Producto();

            productoNuevo.setCantidadStock(stock);
            productoNuevo.setCodigoProducto(codigoProd);
            productoNuevo.setDescripcion(descripcion);
            productoNuevo.setDimensiones(dimensione);;
            productoNuevo.setNombreProducto(nombre);
            productoNuevo.setPrecioProveedor(precioProveedor);
            productoNuevo.setProveedor(proveedor);
            productoNuevo.setGamaProducto(gamaProducto);
                        
            daoProducto.guardaProducto(productoNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo el nuevo producto de manera correcta");
        }
    }

    public void modificarProducto(int id){
        try{
            Producto newProducto = daoProducto.buscarProducto(id);
            if(newProducto != null) {
                newProducto.setProveedor("nuevo");
                daoProducto.actualizarProducto(newProducto);
                System.out.println("Producto modificado correctamente (Proveedor)");
            }
        }catch (Exception e){
            System.out.println("Error al buscar o modificar el producto: " + e.toString());
        }
    }
    
    public Producto buscarProducto(int id){
        Producto producto = daoProducto.buscarProducto(id);
        try{
            if(producto == null){
                System.out.println("No existe un producto con el ID proporcionado");
            }
            return producto;
        }catch(Exception e){
            System.out.println("Ocurrio un error buscando este producto" + e.toString());
        }
        return null;
    }
    
    public void eliminarProducto(int id){
        try{
            daoProducto.eliminarProducto(id);
        }catch(Exception e){
            System.out.println(e.toString() + "No se pudo eliminar el producto de manera correcta");
        }
    }

    public void listarProductos() throws Exception {
        List<Producto> listaProductos = daoProducto.listaProductos();
        imprimirLista(listaProductos);
    }

    public void imprimirLista(List<Producto> listaProductos) {
        for (Producto producto : listaProductos) {
            System.out.println(producto.getIdProducto()+" - "+producto.getNombreProducto()+" - "+producto.getCantidadStock());
        }
    }

    public void listarProductosPorDetalle (String nombreProducto) throws Exception {
        List<Producto> productosDetalle = daoProducto.listarProductosPorDetalle(nombreProducto);
        System.out.println("Productos que contienen '"+nombreProducto+"' en su detalle: ");
        for (Producto producto : productosDetalle) {
            System.out.println(producto.getIdProducto()+" - "+producto.getNombreProducto()+" - "+producto.getDescripcion());
        }
    }

    public void listar10ProductosMasVendidosEnPedidos () throws Exception {
        List<Object[]> productosMasVendidos = daoProducto.listar10ProductosMasVendidosEnPedidos();
        System.out.println("Top 10 productos más vendidos:");
        for (Object[] obj : productosMasVendidos) {
            String nombreProducto = (String) obj[0];
            Long totalVendido = (Long) obj[1];
            System.out.println(nombreProducto + " - Cantidad vendida: " + totalVendido);
        }
    }
}
