package com.example.Techmerch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Techmerch.model.CitaTecnica;
import com.example.Techmerch.citatecnica.*;

@Controller
@RequestMapping("/citastecnica")
public class CitaTecnicaController {

    private final CitaTecnicaService citaTecnicaService;

    public CitaTecnicaController(CitaTecnicaService citaTecnicaService) {
        this.citaTecnicaService = citaTecnicaService;
    }

    @GetMapping("/list")
    public String listaCitasTecnicas(Model model) {
        List<CitaTecnica> citas = citaTecnicaService.listaCitasTecnicas();
        model.addAttribute("citasTecnicas", citas);
        return "private/citas_tecnica/citastecnica";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("citaTecnica", new CitaTecnica());
        return "private/citas_tecnica/citastecnicaagregar";
    }

    @PostMapping("/agregar")
    public String agregarCitaTecnica(@ModelAttribute("citaTecnica") CitaTecnica citaTecnica) {
        citaTecnica.setStatus("ACTIVO");
        citaTecnica.setEstado("Pendiente");
        citaTecnicaService.crearCitaTecnica(citaTecnica);
        return "redirect:/citastecnica/list";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        CitaTecnica citaTecnica = citaTecnicaService.obtenerCitaTecnicaPorId(id);
        model.addAttribute("citaTecnica", citaTecnica);
        return "private/citas_tecnica/citastecnicaeditar";
    }

    @PostMapping("/editar")
    public String editarCitaTecnica(@ModelAttribute("citaTecnica") CitaTecnica citaTecnica) {
        citaTecnicaService.actualizarCitaTecnica(citaTecnica);
        return "redirect:/citastecnica/list";
    }

    @PostMapping("/cambiar-estado")
    public String cambiarEstadoCita(@RequestParam("id") int id, @RequestParam("estado") String estado) {
        citaTecnicaService.cambiarEstadoCita(id, estado);
        return "redirect:/citastecnica/list";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCitaTecnica(@PathVariable("id") int id) {
        citaTecnicaService.eliminarCitaTecnica(id);
        return "redirect:/citastecnica/list";
    }
}