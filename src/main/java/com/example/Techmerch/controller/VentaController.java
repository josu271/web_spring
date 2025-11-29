package com.example.Techmerch.controller;

import com.example.Techmerch.model.Venta;
import com.example.Techmerch.venta.VentaService;
import com.example.Techmerch.empleado.EmpleadoService;
import com.example.Techmerch.cliente.ClienteService;
import com.example.Techmerch.producto.ProductoService;
import com.example.Techmerch.model.Cliente;
import com.example.Techmerch.model.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final EmpleadoService empleadoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService, EmpleadoService empleadoService,
                           ClienteService clienteService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.empleadoService = empleadoService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping({"", "/", "/list"})
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("ventas", ventas);
        return "private/venta/venta";
    }

    // Método GET corregido para aceptar parámetros de búsqueda
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(
            @RequestParam(required = false) String busquedaCliente,
            @RequestParam(required = false) String busquedaProducto,
            Model model) {

        model.addAttribute("venta", new Venta());
        model.addAttribute("empleados", empleadoService.findAll());

        // Manejar búsqueda de clientes
        if (busquedaCliente != null && !busquedaCliente.trim().isEmpty()) {
            List<Cliente> clientesEncontrados = clienteService.buscarClientes(busquedaCliente);
            model.addAttribute("clientesEncontrados", clientesEncontrados);
            model.addAttribute("busquedaCliente", busquedaCliente);
        } else {
            // Si no hay búsqueda, cargar todos los clientes
            model.addAttribute("clientes", clienteService.listaClientes());
        }

        // Manejar búsqueda de productos
        if (busquedaProducto != null && !busquedaProducto.trim().isEmpty()) {
            List<Producto> productosEncontrados = productoService.buscarProductos(busquedaProducto);
            model.addAttribute("productosEncontrados", productosEncontrados);
            model.addAttribute("busquedaProducto", busquedaProducto);
        } else {
            // Si no hay búsqueda, cargar todos los productos
            model.addAttribute("productos", productoService.obtenerTodosProductos());
        }

        return "private/venta/ventaagregar";
    }

    // Mantener los métodos POST de búsqueda por compatibilidad
    @PostMapping("/buscar-cliente")
    public String buscarCliente(@RequestParam String busquedaCliente,
                                @RequestParam(required = false) String busquedaProducto,
                                Model model) {
        // Redirigir al método GET con parámetros
        String redirectUrl = "/ventas/agregar?busquedaCliente=" + busquedaCliente;
        if (busquedaProducto != null && !busquedaProducto.trim().isEmpty()) {
            redirectUrl += "&busquedaProducto=" + busquedaProducto;
        }
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/buscar-producto")
    public String buscarProducto(@RequestParam String busquedaProducto,
                                 @RequestParam(required = false) String busquedaCliente,
                                 Model model) {
        // Redirigir al método GET con parámetros
        String redirectUrl = "/ventas/agregar?busquedaProducto=" + busquedaProducto;
        if (busquedaCliente != null && !busquedaCliente.trim().isEmpty()) {
            redirectUrl += "&busquedaCliente=" + busquedaCliente;
        }
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/agregar")
    public String agregarVenta(@ModelAttribute Venta venta,
                               @RequestParam Map<String, String> allParams,
                               @RequestParam(required = false) String busquedaCliente,
                               @RequestParam(required = false) String busquedaProducto,
                               Model model) {

        // Validar que se haya seleccionado un cliente
        if (venta.getDniCliente() == null) {
            model.addAttribute("error", "Debe seleccionar un cliente");
            return mostrarFormularioAgregar(busquedaCliente, busquedaProducto, model);
        }

        // Procesar productos seleccionados y calcular total
        double totalCalculado = 0.0;
        boolean productosSeleccionados = false;

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("cantidad_") && !entry.getValue().isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(entry.getValue());
                    if (cantidad > 0) {
                        String productId = entry.getKey().replace("cantidad_", "");
                        Producto producto = productoService.obtenerProductoPorId(Integer.parseInt(productId));
                        if (producto != null) {
                            totalCalculado += cantidad * producto.getPrecio().doubleValue();
                            productosSeleccionados = true;
                        }
                    }
                } catch (NumberFormatException e) {
                    // Ignorar valores no numéricos
                }
            }
        }

        // Validar que se hayan seleccionado productos
        if (!productosSeleccionados) {
            model.addAttribute("error", "Debe seleccionar al menos un producto");
            return mostrarFormularioAgregar(busquedaCliente, busquedaProducto, model);
        }

        // Establecer el total calculado
        venta.setTotal(java.math.BigDecimal.valueOf(totalCalculado));
        venta.setStatus("COMPLETADA");

        ventaService.save(venta);
        return "redirect:/ventas/list";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Venta venta = ventaService.findById(id);
        if (venta != null) {
            model.addAttribute("venta", venta);
            model.addAttribute("empleados", empleadoService.findAll());
            model.addAttribute("clientes", clienteService.listaClientes());
            return "private/venta/ventaeditar";
        }
        return "redirect:/ventas/list";
    }

    @PostMapping("/editar")
    public String editarVenta(@ModelAttribute Venta venta) {
        ventaService.update(venta);
        return "redirect:/ventas/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id) {
        ventaService.delete(id);
        return "redirect:/ventas/list";
    }
}