package com.egg.Servicios;

import java.util.Date;
import java.util.List;

import com.egg.Entidades.Cliente;
import com.egg.Entidades.Pago;
import com.egg.Persistence.PagoDAO;

public class PagoServicio {
    private final PagoDAO daoPago;
    
    public PagoServicio() {
        this.daoPago = new PagoDAO();
    }

    public void crearPago( Date fechaPago, String formaPago, String idTransaction, double total, Cliente cliente) {
        try {
            Pago pagoNuevo = new Pago();

            pagoNuevo.setFechaPago(fechaPago);
            pagoNuevo.setFormaPago(formaPago);
            pagoNuevo.setIdTransaccion(idTransaction);
            pagoNuevo.setTotal(total);
            pagoNuevo.setCliente(cliente);
            
            daoPago.guardarPago(pagoNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo el nuevo pago de manera correcta");
        }
    }

    public void modificarPago(int id){
        try{
            Pago newPago = daoPago.buscarPago(id);
            if(newPago != null) {
                newPago.setFormaPago("Efectivo");
                daoPago.actualizarPago(newPago);
                System.out.println("Pago modificado correctamente (Forma de Pago)");
            }
        }catch (Exception e){
            System.out.println("Error al buscar o modificar el pago: " + e.toString());
        }
    }
    
    public Pago buscarPago(int id){
        Pago pago = daoPago.buscarPago(id);
        try{
            if(pago == null){
                System.out.println("No existe un pago con el ID proporcionado");
            }
            return pago;
        }catch(Exception e){
            System.out.println("Ocurrio un error buscando este pago" + e.toString());
        }
        return null;
    }
    
    public void eliminarPago(int id){
        try{
            daoPago.eliminarPago(id);
        }catch(Exception e){
            System.out.println(e.toString() + "No se pudo eliminar el pago de manera correcta");
        }
    }

    public void listarPagosEntreFechas (Date fechaInicio, Date fechaFin) throws Exception {
        List<Pago> pagos = daoPago.listarPagosEntreFechas(fechaInicio, fechaFin);
        System.out.println("Pagos realizados entre " + fechaInicio + " y " + fechaFin + ":");
        for (Pago pago : pagos) {
            System.out.println("ID Transacción: " + pago.getIdTransaccion() + " - Fecha: " + pago.getFechaPago() + ", Cantidad: " + pago.getTotal());
        }
    }
}
