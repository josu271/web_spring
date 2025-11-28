package com.example.Techmerch.empleado;

import com.example.Techmerch.model.Empleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/list")
    public String listarEmpleados(Model model, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        List<Empleado> empleados = empleadoService.findAll();
        model.addAttribute("empleados", empleados);
        return "private/empleado/empleado";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("empleado", new Empleado());
        return "private/empleado/empleadoagregar";
    }

    @PostMapping("/agregar")
    public String agregarEmpleado(@ModelAttribute Empleado empleado, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        empleadoService.save(empleado);
        return "redirect:/empleado/list";
    }

    @GetMapping("/editar/{dni}")
    public String mostrarFormularioEditar(@PathVariable Integer dni, Model model, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        Empleado empleado = empleadoService.findById(dni);
        model.addAttribute("empleado", empleado);
        return "private/empleado/empleadoeditar";
    }

    @PostMapping("/editar")
    public String editarEmpleado(@ModelAttribute Empleado empleado, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        empleadoService.update(empleado);
        return "redirect:/empleado/list";
    }

    @PostMapping("/eliminar/{dni}")
    public String eliminarEmpleado(@PathVariable Integer dni, HttpSession session) {
        if (session.getAttribute("empleado") == null) {
            return "redirect:/login";
        }

        empleadoService.delete(dni);
        return "redirect:/empleado/list";
    }
}