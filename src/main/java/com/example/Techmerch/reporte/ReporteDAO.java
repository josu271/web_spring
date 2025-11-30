package com.example.Techmerch.reporte;

import com.example.Techmerch.model.Reporte;
import java.util.List;
import java.math.BigDecimal;

public interface ReporteDAO {
    List<Reporte> obtenerVentasPorPeriodo(String periodo);
    List<Reporte> obtenerTopProductos(Integer limite);
    List<Reporte> obtenerCategoriasMasVendidas();
    BigDecimal obtenerIngresosTotales(String periodo);
    BigDecimal obtenerPromedioVenta(String periodo);
    Integer obtenerTotalCitasPorEstado(String estado);
}
