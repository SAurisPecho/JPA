package com.egg;

import java.util.Date;
import java.text.SimpleDateFormat;
// import com.egg.Servicios.ClienteServicio;
// import com.egg.Servicios.PedidoServicio;
// import com.egg.Servicios.ProductoServicio;
// import com.egg.Servicios.OficinaServicio;
// import com.egg.Servicios.EmpleadoServicio;
import com.egg.Servicios.PagoServicio;

public class Application {

    public static void main(String[] args) throws Exception {
        // OficinaServicio oficinaServicio = new OficinaServicio();
        // //Imprimir Todas las Oficinas
        // System.out.println("************OFICINAS DISPONIBLES*********");
        // System.out.println("ID OFICINA // CIUDAD OFICINA // PAIS OFICINA");
        // oficinaServicio.listarOficinas();

        // ProductoServicio productoServicio = new ProductoServicio();
        // //Listar todos los productos que en su detalle tengan un producto específico que se pasa el nombre como parámetro.
        // productoServicio.listarProductosPorDetalle("Cerezo");

        // PedidoServicio pedidoServicio = new PedidoServicio();
        // //Listar todos los pedidos de un cliente que se pasa por nombre como parámetro y que los pedidos están entre un rango de fechas que también se recibe como parámetro.
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Date fechaInicio = sdf.parse("2006-01-01");
        // Date fechaFin = sdf.parse("2010-12-31");
        // pedidoServicio.listarPedidosClienteEntreFechas("Lasas S.A.", fechaInicio, fechaFin);

        // ClienteServicio clienteServicio = new ClienteServicio();
        // //Listar todos los clientes de un empleado de contacto específico que se pasa como parámetro. 
        // clienteServicio.listarClientesPorEmpleado("Mariano");

        // EmpleadoServicio empleadoServicio = new EmpleadoServicio();
        // //Listar todos los empleados de una oficina específica, la cual se pasa el código o nombre como parámetro.
        // empleadoServicio.listarEmpleadosPorCodigoOficina("BOS-USA");
        // //Listar todos los empleados que son jefes.
        // empleadoServicio.listarEmpleadosJefes();

        // //Listar los 10 productos más vendidos en los pedidos. Investiga cómo sumar los productos en el listado y cómo funcionan los límites en los listados.
        // productoServicio.listar10ProductosMasVendidosEnPedidos();

        PagoServicio pagoServicio = new PagoServicio();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = sdf.parse("2006-01-01");
        Date fechaFin = sdf.parse("2010-12-31");
        //Listar todos los pagos que se hicieron entre un rango de fechas que se recibe por parámetro.
        pagoServicio.listarPagosEntreFechas(fechaInicio, fechaFin);
    }
}
