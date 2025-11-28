package com.example.Techmerch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // --- PÚBLICAS ---
    @GetMapping("/")
    public String home() {
        return "public/index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "public/productos";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "public/contacto";
    }

    @GetMapping("/serviciotecnico")
    public String servicioTecnico() {
        return "public/serviciotecnico";
    }

    // --- PRIVADAS ---}

    // ---------- PRODUCTOS ----------
    @GetMapping("/articulo")
    public String articulo() {
        return "private/producto/articulo";
    }

    @GetMapping("/articulo/agregar")
    public String articuloAgregar() {
        return "private/producto/articuloagregar";
    }

    @GetMapping("/articulo/editar")
    public String articuloEditar() {
        return "private/producto/articuloeditar";
    }

    // ---------- VENTAS ----------
    @GetMapping("/ventas")
    public String ventas() {
        return "private/venta/ventas";
    }

    @GetMapping("/ventas/agregar")
    public String ventasAgregar() {
        return "private/venta/ventasagregar";
    }

    @GetMapping("/ventas/editar")
    public String ventasEditar() {
        return "private/venta/ventaseditar";
    }


    // ---------- CALENDARIO ----------
    @GetMapping("/calendario")
    public String calendario() {
        return "private/calendario";
    }

    @GetMapping("/reporte")
    public String reporte() {
        return "private/reporte";
    }
}
