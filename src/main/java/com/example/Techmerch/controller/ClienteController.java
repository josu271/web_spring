package com.example.Techmerch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Techmerch.model.Cliente;
import com.example.Techmerch.cliente.ClienteService;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Mapeo principal para lista de clientes
    @GetMapping({"", "/", "/list"})
    public String listaClientes(Model model) {
        model.addAttribute("clientes", clienteService.listaClientes());
        return "private/cliente/cliente";
    }

    @GetMapping("/api/list")
    @ResponseBody
    public List<Cliente> obtenerClientes() {
        return clienteService.listaClientes();
    }

    @GetMapping("/ver")
    public String obtenerCliente(@RequestParam("dni") Integer dni, Model model) {
        Cliente cliente = clienteService.obtenerClientePorDni(dni);
        model.addAttribute("cliente", cliente);
        return "private/cliente/ver";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "private/cliente/clienteagregar";
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes/list";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("dni") Integer dni, Model model) {
        Cliente cliente = clienteService.obtenerClientePorDni(dni);
        model.addAttribute("cliente", cliente);
        return "private/cliente/clienteeditar";
    }

    @PostMapping("/editar")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes/list";
    }

    @PostMapping("/desactivar")
    public String desactivarCliente(@RequestParam("dni") Integer dni) {
        clienteService.desactivarCliente(dni);
        return "redirect:/clientes/list";
    }

    @PostMapping("/activar")
    public String activarCliente(@RequestParam("dni") Integer dni) {
        clienteService.activarCliente(dni);
        return "redirect:/clientes/list";
    }
}