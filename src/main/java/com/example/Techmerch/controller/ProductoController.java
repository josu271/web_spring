package com.example.Techmerch.controller;

import com.example.Techmerch.model.Producto;
import com.example.Techmerch.producto.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosProductos();
        model.addAttribute("productos", productos);
        return "private/producto/articulo";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());

        // Datos de ejemplo para categorías (deberías crear un servicio para categorías)
        // model.addAttribute("categorias", categoriaService.obtenerTodasCategorias());

        return "private/producto/articuloagregar";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto) {
        // Validaciones básicas
        if (producto.getPrecio() == null) {
            producto.setPrecio(BigDecimal.ZERO);
        }
        if (producto.getStock() == null) {
            producto.setStock(0);
        }

        productoService.crearProducto(producto);
        return "redirect:/producto";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("id") Integer id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            return "redirect:/producto";
        }
        model.addAttribute("producto", producto);

        // model.addAttribute("categorias", categoriaService.obtenerTodasCategorias());

        return "private/producto/articuloeditar";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.actualizarProducto(producto);
        return "redirect:/producto";
    }

    @GetMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id") Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto";
    }

    @GetMapping("/activar")
    public String activarProducto(@RequestParam("id") Integer id) {
        productoService.activarProducto(id);
        return "redirect:/producto";
    }

    @GetMapping("/desactivar")
    public String desactivarProducto(@RequestParam("id") Integer id) {
        productoService.desactivarProducto(id);
        return "redirect:/producto";
    }
}