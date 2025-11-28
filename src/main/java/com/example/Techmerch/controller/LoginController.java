package com.example.Techmerch.controller;

import com.example.Techmerch.empleado.EmpleadoService;
import com.example.Techmerch.model.Empleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final EmpleadoService empleadoService;

    public LoginController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    @GetMapping("/login")
    public String mostrarLogin() {
        return "public/login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("dni") Integer dni,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Empleado empleado = empleadoService.login(dni, password);

        if (empleado != null) {
            session.setAttribute("empleado", empleado);
            session.setAttribute("dni", empleado.getDniEmpleado());
            session.setAttribute("nombre", empleado.getNombre() + " " + empleado.getApellido());
            session.setAttribute("cargo", empleado.getCargo());
            return "redirect:/panel";
        } else {
            model.addAttribute("error", "DNI o contraseña incorrectos");
            return "public/login/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/panel")
    public String mostrarPanel(Model model, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("nombre", session.getAttribute("nombre"));
        model.addAttribute("cargo", session.getAttribute("cargo"));

        return "private/panel";
    }
}