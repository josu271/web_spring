package com.example.Techmerch.controller;

import com.example.Techmerch.reporte.DashboardData;
import com.example.Techmerch.reporte.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        DashboardData datos = reporteService.obtenerDatosDashboard();

        model.addAttribute("ventasDelDia", datos.getVentasDelDia());
        model.addAttribute("ventasDeLaSemana", datos.getVentasDeLaSemana());
        model.addAttribute("ventasDelMes", datos.getVentasDelMes());
        model.addAttribute("ventasSemanales", datos.getVentasSemanales());
        model.addAttribute("topProductos", datos.getTopProductos());
        model.addAttribute("categoriasVendidas", datos.getCategoriasVendidas());
        model.addAttribute("promedioVenta", datos.getPromedioVenta());
        model.addAttribute("citasPendientes", datos.getCitasPendientes());
        model.addAttribute("citasEnProgreso", datos.getCitasEnProgreso());
        model.addAttribute("citasCompletadas", datos.getCitasCompletadas());

        return "private/reporte"; // Retorna a la vista del dashboard
    }

    @GetMapping("/vista")
    public String mostrarReportes(Model model) {
        DashboardData datos = reporteService.obtenerDatosDashboard();

        model.addAttribute("ventasDelDia", datos.getVentasDelDia());
        model.addAttribute("ventasDeLaSemana", datos.getVentasDeLaSemana());
        model.addAttribute("ventasDelMes", datos.getVentasDelMes());
        model.addAttribute("ventasSemanales", datos.getVentasSemanales());
        model.addAttribute("topProductos", datos.getTopProductos());
        model.addAttribute("categoriasVendidas", datos.getCategoriasVendidas());
        model.addAttribute("promedioVenta", datos.getPromedioVenta());

        return "private/reporte"; // Retorna a la vista de reportes específica
    }
}