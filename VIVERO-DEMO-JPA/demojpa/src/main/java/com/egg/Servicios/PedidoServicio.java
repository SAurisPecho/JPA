package com.egg.Servicios;

import java.util.Date;
import java.util.List;

import com.egg.Entidades.Cliente;
import com.egg.Entidades.Pedido;
import com.egg.Persistence.PedidoDAO;

public class PedidoServicio {
    private final PedidoDAO daoPedido;

    public PedidoServicio() {
        this.daoPedido = new PedidoDAO();
    }

    public void crearPedido( String codigoPedido, String comentarios, String estado, Date fechaEntrega, Date fechaEsperada, Date fechaPedido, Cliente cliente) {
        try {
            Pedido pedidoNuevo = new Pedido();

            pedidoNuevo.setCodigoPedido(codigoPedido);
            pedidoNuevo.setComentarios(comentarios);
            pedidoNuevo.setEstado(estado);
            pedidoNuevo.setFechaEntrega(fechaEntrega);
            pedidoNuevo.setFechaEsperada(fechaEsperada);
            pedidoNuevo.setFechaPedido(fechaPedido);
            pedidoNuevo.setCliente(cliente);
            
            daoPedido.guardaPedido(pedidoNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo el nuevo pedido de manera correcta");
        }
    }

    public void modificarPedido(int id){
        try{
            Pedido newPedido = daoPedido.buscarPedido(id);
            if(newPedido != null) {
                newPedido.setEstado("Entregado");
                daoPedido.actualizarPedido(newPedido);
                System.out.println("Pedido modificado correctamente (Estado)");
            }
        }catch (Exception e){
            System.out.println("Error al buscar o modificar el pedido: " + e.toString());
        }
    }
    
    public Pedido buscarPedido(int id){
        Pedido pedido = daoPedido.buscarPedido(id);
        try{
            if(pedido == null){
                System.out.println("No existe un pedido con el ID proporcionado");
            }
            return pedido;
        }catch(Exception e){
            System.out.println("Ocurrio un error buscando este pedido" + e.toString());
        }
        return null;
    }
    
    public void eliminarPedido(int id){
        try{
            daoPedido.eliminarPedido(id);
        }catch(Exception e){
            System.out.println(e.toString() + "No se pudo eliminar el pedido de manera correcta");
        }
    }

    public void listarPedidosFechaEntregaRetrasados () throws Exception {
        List<Pedido> pedidosFechaEntrega = daoPedido.listarPedidosFechaEntregaRetrasados();
        imprimirLista(pedidosFechaEntrega);
    }

    public void imprimirLista(List<Pedido> listaRecibida) {
        for (Pedido pedido : listaRecibida) {
            System.out.println(pedido.getIdPedido()+" - "+pedido.getEstado()+" - "+pedido.getComentarios());
        }
    }

    public void listarPedidosClienteEntreFechas (String nombreCliente, Date fechaInicio, Date fechaFin) throws Exception {
        List<Pedido> pedidosClienteFechas = daoPedido.listarPedidosClienteEntreFechas(nombreCliente, fechaInicio, fechaFin);
        System.out.println("Pedidos de " + nombreCliente + " entre " + fechaInicio + " y " + fechaFin + ":");
        for (Pedido pedido : pedidosClienteFechas) {
            System.out.println("ID: " + pedido.getIdPedido() + 
                            ", Fecha: " + pedido.getFechaPedido() + 
                            ", Estado: " + pedido.getEstado());
        }
    }
}
