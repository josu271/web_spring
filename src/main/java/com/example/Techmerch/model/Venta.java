package com.example.Techmerch.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta {
    private Integer idVenta;
    private Integer dniCliente;
    private Integer dniEmpleado;
    private LocalDateTime fechaVenta;
    private String metodoPago;
    private BigDecimal total;
    private String status;

    public Venta() {
    }

    public Venta(Integer idVenta, Integer dniCliente, Integer dniEmpleado, LocalDateTime fechaVenta,
                 String metodoPago, BigDecimal total, String status) {
        this.idVenta = idVenta;
        this.dniCliente = dniCliente;
        this.dniEmpleado = dniEmpleado;
        this.fechaVenta = fechaVenta;
        this.metodoPago = metodoPago;
        this.total = total;
        this.status = status;
    }

    // Getters y Setters
    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    public Integer getDniCliente() { return dniCliente; }
    public void setDniCliente(Integer dniCliente) { this.dniCliente = dniCliente; }

    public Integer getDniEmpleado() { return dniEmpleado; }
    public void setDniEmpleado(Integer dniEmpleado) { this.dniEmpleado = dniEmpleado; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}