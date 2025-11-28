package com.example.Techmerch.model;

import java.time.LocalDateTime;

public class CitaTecnica {
    private Integer idCitaTecnica;
    private Integer dniCliente;
    private Integer dniEmpleado;
    private String servicio;
    private String estado;
    private String descripcion;
    private LocalDateTime fechaProgramada;
    private String status;

    public CitaTecnica() {
        // Constructor vacío
    }

    public CitaTecnica(Integer idCitaTecnica, Integer dniCliente, Integer dniEmpleado,
                       String servicio, String estado, String descripcion,
                       LocalDateTime fechaProgramada, String status) {
        this.idCitaTecnica = idCitaTecnica;
        this.dniCliente = dniCliente;
        this.dniEmpleado = dniEmpleado;
        this.servicio = servicio;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaProgramada = fechaProgramada;
        this.status = status;
    }

    // Getters y Setters
    public Integer getIdCitaTecnica() {
        return idCitaTecnica;
    }

    public void setIdCitaTecnica(Integer idCitaTecnica) {
        this.idCitaTecnica = idCitaTecnica;
    }

    public Integer getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Integer dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Integer getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Integer dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}