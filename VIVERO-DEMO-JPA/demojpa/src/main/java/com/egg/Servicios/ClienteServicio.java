package com.egg.Servicios;

import com.egg.Entidades.Cliente;
import com.egg.Persistence.ClienteDAO;

public class ClienteServicio {
    private final ClienteDAO daoCliente;

    public ClienteServicio() {
        this.daoCliente = new ClienteDAO();
    }

    public void guardarClient (String apellidoContact, String ciudad, String codigoCliente, 
                        String codigoPostal, String fax, double limiteCredito, String nombreCliente, 
                        String nombreContacto, String pais, String region, String telefono) {
        try {
            Cliente clienteNuevo = new Cliente();

            clienteNuevo.setApellidoContacto(apellidoContact);
            clienteNuevo.setCiudad(ciudad);
            clienteNuevo.setCodigoCliente(codigoCliente);
            clienteNuevo.setCodigoPostal(codigoPostal);
            clienteNuevo.setFax(fax);
            clienteNuevo.setLimiteCredito(limiteCredito);
            clienteNuevo.setNombreCliente(nombreCliente);
            clienteNuevo.setNombreContacto(nombreContacto);
            clienteNuevo.setPais(pais);
            clienteNuevo.setRegion(region);
            clienteNuevo.setTelefono(telefono);

            daoCliente.guardarCliente(clienteNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + "No se guardo el nuevo cliente de manera correcta");
        }
    }

    public Cliente buscarClient (int idCliente) throws Exception {
        Cliente cliente = daoCliente.buscarCliente(idCliente);
        try {
            if (cliente == null) {
                System.out.println("No existe en el registro un cliente con el ID proporcionado");
            }
            return cliente;
        } catch (Exception e) {
            System.out.println("Ocurrio un error buscando al cliente" + e.toString());
        }
        return null;
    }

    public void modificarCliente(int idCliente) {
        try {
            Cliente newCliente = daoCliente.buscarCliente(idCliente);
            if (newCliente != null) {
                newCliente.setLimiteCredito(18520);
                daoCliente.actualizarCliente(newCliente);
                System.out.println("Cliente modificado correctamente (limite credito)");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o modificar el cliente: " + e.toString());
        }
    }

    public void removerCliente (int idCliente) {
        try {
            daoCliente.removerCliente(idCliente);
        } catch (Exception e) {
            System.out.println(e.toString() + " No se pudo remover el cliente.");
        }
    }
}
