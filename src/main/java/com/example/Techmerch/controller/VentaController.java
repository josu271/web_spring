package com.example.Techmerch.controller;

import com.example.Techmerch.model.Venta;
import com.example.Techmerch.model.Producto;
import com.example.Techmerch.model.DetalleVenta;
import com.example.Techmerch.venta.VentaService;
import com.example.Techmerch.empleado.EmpleadoService;
import com.example.Techmerch.cliente.ClienteService;
import com.example.Techmerch.producto.ProductoService;
import com.example.Techmerch.venta.DetalleVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/ventas")
@SessionAttributes({"productosSeleccionados", "cantidades"})
public class VentaController {

    private final VentaService ventaService;
    private final EmpleadoService empleadoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final DetalleVentaService detalleVentaService;

    public VentaController(VentaService ventaService, EmpleadoService empleadoService,
                           ClienteService clienteService, ProductoService productoService,
                           DetalleVentaService detalleVentaService) {
        this.ventaService = ventaService;
        this.empleadoService = empleadoService;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.detalleVentaService = detalleVentaService;
    }

    @ModelAttribute("productosSeleccionados")
    public Map<Integer, Producto> productosSeleccionados() {
        return new HashMap<>();
    }

    @ModelAttribute("cantidades")
    public Map<Integer, Integer> cantidades() {
        return new HashMap<>();
    }

    @GetMapping({"", "/", "/list"})
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("ventas", ventas);
        return "private/venta/venta";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("empleados", empleadoService.findAll());
        model.addAttribute("clientes", clienteService.listaClientes());
        model.addAttribute("productos", productoService.obtenerTodosProductos());

        if (!model.containsAttribute("productosSeleccionados")) {
            model.addAttribute("productosSeleccionados", new HashMap<Integer, Producto>());
        }
        if (!model.containsAttribute("cantidades")) {
            model.addAttribute("cantidades", new HashMap<Integer, Integer>());
        }

        return "private/venta/ventaagregar";
    }

    @PostMapping("/agregar-producto")
    public String agregarProductoAVenta(
            @RequestParam("productoId") Integer productoId,
            @RequestParam("cantidad") Integer cantidad,
            @ModelAttribute("productosSeleccionados") Map<Integer, Producto> productosSeleccionados,
            @ModelAttribute("cantidades") Map<Integer, Integer> cantidades,
            RedirectAttributes redirectAttributes) {

        if (productoId == null || cantidad == null || cantidad <= 0) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar un producto y una cantidad válida");
            return "redirect:/ventas/agregar";
        }

        Producto producto = productoService.obtenerProductoPorId(productoId);
        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/ventas/agregar";
        }

        productosSeleccionados.put(productoId, producto);
        cantidades.put(productoId, cantidad);

        redirectAttributes.addFlashAttribute("success", "Producto agregado: " + producto.getNombre());
        return "redirect:/ventas/agregar";
    }

    @PostMapping("/remover-producto")
    public String removerProducto(
            @RequestParam("productoId") Integer productoId,
            @ModelAttribute("productosSeleccionados") Map<Integer, Producto> productosSeleccionados,
            @ModelAttribute("cantidades") Map<Integer, Integer> cantidades,
            RedirectAttributes redirectAttributes) {

        productosSeleccionados.remove(productoId);
        cantidades.remove(productoId);

        redirectAttributes.addFlashAttribute("success", "Producto removido");
        return "redirect:/ventas/agregar";
    }

    @PostMapping("/agregar")
    public String agregarVenta(
            @ModelAttribute Venta venta,
            @ModelAttribute("productosSeleccionados") Map<Integer, Producto> productosSeleccionados,
            @ModelAttribute("cantidades") Map<Integer, Integer> cantidades,
            RedirectAttributes redirectAttributes) {

        try {
            // Validaciones básicas
            if (venta.getDniCliente() == null) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un cliente");
                return "redirect:/ventas/agregar";
            }

            if (venta.getDniEmpleado() == null) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un empleado");
                return "redirect:/ventas/agregar";
            }

            if (venta.getMetodoPago() == null || venta.getMetodoPago().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un método de pago");
                return "redirect:/ventas/agregar";
            }

            // Validar productos
            if (productosSeleccionados.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Debe agregar al menos un producto");
                return "redirect:/ventas/agregar";
            }

            // Calcular total y preparar detalles
            BigDecimal total = BigDecimal.ZERO;
            List<DetalleVenta> detalles = new ArrayList<>();

            for (Map.Entry<Integer, Producto> entry : productosSeleccionados.entrySet()) {
                Integer productoId = entry.getKey();
                Producto producto = entry.getValue();
                Integer cantidad = cantidades.get(productoId);

                if (cantidad != null && cantidad > 0) {
                    BigDecimal precio = producto.getPrecio();
                    BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
                    total = total.add(subtotal);

                    // Crear detalle de venta
                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setIdProducto(productoId);
                    detalle.setCantidad(cantidad);
                    detalle.setPrecioUnitario(precio);
                    detalle.setSubtotal(subtotal);
                    detalle.setStatus("ACTIVO");

                    detalles.add(detalle);
                }
            }

            if (total.compareTo(BigDecimal.ZERO) <= 0) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar al menos un producto con cantidad mayor a 0");
                return "redirect:/ventas/agregar";
            }

            // Configurar datos de la venta
            venta.setFechaVenta(LocalDateTime.now());
            venta.setTotal(total);
            venta.setStatus("COMPLETADA");

            // Guardar venta con detalles
            Integer idVenta = ventaService.saveWithDetails(venta, detalles);

            // Limpiar carrito después de guardar
            productosSeleccionados.clear();
            cantidades.clear();

            redirectAttributes.addFlashAttribute("success", "Venta #" + idVenta + " registrada exitosamente con " + detalles.size() + " producto(s)");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar la venta: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/ventas/list";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleVenta(@PathVariable Integer id, Model model) {
        try {
            Venta venta = ventaService.findById(id);
            if (venta == null) {
                return "redirect:/ventas/list";
            }

            List<DetalleVenta> detalles = detalleVentaService.findByVentaId(id);

            // Obtener información de productos para cada detalle
            List<Map<String, Object>> detallesCompletos = new ArrayList<>();
            for (DetalleVenta detalle : detalles) {
                Producto producto = productoService.obtenerProductoPorId(detalle.getIdProducto());

                Map<String, Object> detalleCompleto = new HashMap<>();
                detalleCompleto.put("detalle", detalle);
                detalleCompleto.put("producto", producto);

                detallesCompletos.add(detalleCompleto);
            }

            model.addAttribute("venta", venta);
            model.addAttribute("detallesCompletos", detallesCompletos);
            model.addAttribute("totalProductos", detalles.size());

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/ventas/list";
        }

        return "private/venta/detalle";
    }

    @GetMapping("/limpiar-carrito")
    public String limpiarCarrito(
            @ModelAttribute("productosSeleccionados") Map<Integer, Producto> productosSeleccionados,
            @ModelAttribute("cantidades") Map<Integer, Integer> cantidades,
            RedirectAttributes redirectAttributes) {

        productosSeleccionados.clear();
        cantidades.clear();

        redirectAttributes.addFlashAttribute("success", "Carrito limpiado");
        return "redirect:/ventas/agregar";
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
    public String editarVenta(@ModelAttribute Venta venta, RedirectAttributes redirectAttributes) {
        try {
            ventaService.update(venta);
            redirectAttributes.addFlashAttribute("success", "Venta actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la venta");
        }
        return "redirect:/ventas/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            ventaService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Venta anulada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al anular la venta");
        }
        return "redirect:/ventas/list";
    }
}