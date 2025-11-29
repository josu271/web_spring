package com.example.Techmerch.model;

import java.math.BigDecimal;

public class DetalleVenta {
    private Integer idDetalle;
    private Integer idProducto;
    private Integer idVenta;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String status;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer idDetalle, Integer idProducto, Integer idVenta, Integer cantidad,
                        BigDecimal precioUnitario, BigDecimal subtotal, String status) {
        this.idDetalle = idDetalle;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.status = status;
    }

    // Getters y Setters
    public Integer getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Integer idDetalle) { this.idDetalle = idDetalle; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}