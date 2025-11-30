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

}
