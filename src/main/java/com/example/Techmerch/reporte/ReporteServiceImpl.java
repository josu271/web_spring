package com.example.Techmerch.reporte;

import com.example.Techmerch.model.Reporte;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteDAO reporteDAO;

    public ReporteServiceImpl(ReporteDAO reporteDAO) {
        this.reporteDAO = reporteDAO;
    }

    @Override
    public List<Reporte> obtenerVentasDelDia() {
        return reporteDAO.obtenerVentasPorPeriodo("dia");
    }

    @Override
    public List<Reporte> obtenerVentasDeLaSemana() {
        return reporteDAO.obtenerVentasPorPeriodo("semana");
    }

    @Override
    public List<Reporte> obtenerVentasDelMes() {
        return reporteDAO.obtenerVentasPorPeriodo("mes");
    }

    @Override
    public List<Reporte> obtenerVentasDelAnio() {
        return reporteDAO.obtenerVentasPorPeriodo("año");
    }

    @Override
    public List<Reporte> obtenerTop5Productos() {
        return reporteDAO.obtenerTopProductos(5);
    }

    @Override
    public List<Reporte> obtenerCategoriasMasVendidas() {
        return reporteDAO.obtenerCategoriasMasVendidas();
    }


    @Override
    public DashboardData obtenerDatosDashboard() {
        DashboardData data = new DashboardData();

        try {
            // Ventas por periodo
            data.setVentasDelDia(reporteDAO.obtenerIngresosTotales("dia"));
            data.setVentasDeLaSemana(reporteDAO.obtenerIngresosTotales("semana"));
            data.setVentasDelMes(reporteDAO.obtenerIngresosTotales("mes"));

            // Datos para gráficos
            data.setVentasSemanales(obtenerVentasDeLaSemana());
            data.setTopProductos(obtenerTop5Productos());
            data.setCategoriasVendidas(obtenerCategoriasMasVendidas());

            // Promedio de venta
            data.setPromedioVenta(reporteDAO.obtenerPromedioVenta("mes"));

            // Citas por estado
            data.setCitasPendientes(reporteDAO.obtenerTotalCitasPorEstado("Pendiente"));
            data.setCitasEnProgreso(reporteDAO.obtenerTotalCitasPorEstado("En Progreso"));
            data.setCitasCompletadas(reporteDAO.obtenerTotalCitasPorEstado("Completada"));

        } catch (Exception e) {
            System.err.println("Error al obtener datos del dashboard: " + e.getMessage());
            // Establecer valores por defecto en caso de error
            data.setVentasDelDia(BigDecimal.ZERO);
            data.setVentasDeLaSemana(BigDecimal.ZERO);
            data.setVentasDelMes(BigDecimal.ZERO);
            data.setPromedioVenta(BigDecimal.ZERO);
            data.setCitasPendientes(0);
            data.setCitasEnProgreso(0);
            data.setCitasCompletadas(0);
            data.setVentasSemanales(List.of());
            data.setTopProductos(List.of());
            data.setCategoriasVendidas(List.of());
        }

        return data;
    }

}