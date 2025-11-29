package com.example.Techmerch.cliente;
import com.example.Techmerch.model.Cliente;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteServiceImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listaClientes() {
        return clienteDAO.listaClientes();
    }

    public Cliente obtenerClientePorDni(Integer dni) {
        return clienteDAO.obtenerClientePorDni(dni);
    }

    public void crearCliente(Cliente cliente) {
        clienteDAO.crearCliente(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizarCliente(cliente);
    }

    public void desactivarCliente(Integer dni) {
        clienteDAO.cambiarEstadoCliente(dni, "INACTIVO");
    }

    public void activarCliente(Integer dni) {
        clienteDAO.cambiarEstadoCliente(dni, "ACTIVO");
    }
    @Override
    public List<Cliente> buscarClientes(String busqueda) {
        System.out.println("Buscando clientes con: " + busqueda);
        List<Cliente> resultados = clienteDAO.buscarClientes(busqueda);
        System.out.println("Resultados encontrados: " + resultados.size());
        return resultados;
    }

}