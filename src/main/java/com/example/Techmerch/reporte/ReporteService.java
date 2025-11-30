package com.example.Techmerch.reporte;

import com.example.Techmerch.model.Reporte;
import java.util.List;
import java.math.BigDecimal;


public interface ReporteService {
    List<Reporte> obtenerVentasDelDia();
    List<Reporte> obtenerVentasDeLaSemana();
    List<Reporte> obtenerVentasDelMes();
    List<Reporte> obtenerVentasDelAnio();
    List<Reporte> obtenerTop5Productos();
    List<Reporte> obtenerCategoriasMasVendidas();
    DashboardData obtenerDatosDashboard();
}
