package com.example.Techmerch.reporte;

import com.example.Techmerch.model.Reporte;
import java.math.BigDecimal;
import java.util.List;

public class DashboardData {
    private BigDecimal ventasDelDia;
    private BigDecimal ventasDeLaSemana;
    private BigDecimal ventasDelMes;
    private List<Reporte> ventasSemanales;
    private List<Reporte> topProductos;
    private List<Reporte> categoriasVendidas;
    private BigDecimal promedioVenta;
    private Integer citasPendientes;
    private Integer citasEnProgreso;
    private Integer citasCompletadas;

    // Getters y Setters
    public BigDecimal getVentasDelDia() {
        return ventasDelDia;
    }

    public void setVentasDelDia(BigDecimal ventasDelDia) {
        this.ventasDelDia = ventasDelDia;
    }

    public BigDecimal getVentasDeLaSemana() {
        return ventasDeLaSemana;
    }

    public void setVentasDeLaSemana(BigDecimal ventasDeLaSemana) {
        this.ventasDeLaSemana = ventasDeLaSemana;
    }

    public BigDecimal getVentasDelMes() {
        return ventasDelMes;
    }

    public void setVentasDelMes(BigDecimal ventasDelMes) {
        this.ventasDelMes = ventasDelMes;
    }

    public List<Reporte> getVentasSemanales() {
        return ventasSemanales;
    }

    public void setVentasSemanales(List<Reporte> ventasSemanales) {
        this.ventasSemanales = ventasSemanales;
    }

    public List<Reporte> getTopProductos() {
        return topProductos;
    }

    public void setTopProductos(List<Reporte> topProductos) {
        this.topProductos = topProductos;
    }

    public List<Reporte> getCategoriasVendidas() {
        return categoriasVendidas;
    }

    public void setCategoriasVendidas(List<Reporte> categoriasVendidas) {
        this.categoriasVendidas = categoriasVendidas;
    }

    public BigDecimal getPromedioVenta() {
        return promedioVenta;
    }

    public void setPromedioVenta(BigDecimal promedioVenta) {
        this.promedioVenta = promedioVenta;
    }

    public Integer getCitasPendientes() {
        return citasPendientes;
    }

    public void setCitasPendientes(Integer citasPendientes) {
        this.citasPendientes = citasPendientes;
    }

    public Integer getCitasEnProgreso() {
        return citasEnProgreso;
    }

    public void setCitasEnProgreso(Integer citasEnProgreso) {
        this.citasEnProgreso = citasEnProgreso;
    }

    public Integer getCitasCompletadas() {
        return citasCompletadas;
    }

    public void setCitasCompletadas(Integer citasCompletadas) {
        this.citasCompletadas = citasCompletadas;
    }
}