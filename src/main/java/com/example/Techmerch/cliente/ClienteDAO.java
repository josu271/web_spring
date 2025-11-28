package com.example.Techmerch.cliente;
import com.example.Techmerch.model.Cliente;

import java.util.List;

public interface ClienteDAO {

    public List<Cliente> listaClientes();
    public Cliente obtenerClientePorDni(Integer dni);
    public void crearCliente(Cliente cliente);
    public void actualizarCliente(Cliente cliente);
    public void cambiarEstadoCliente(Integer dni, String status);
}