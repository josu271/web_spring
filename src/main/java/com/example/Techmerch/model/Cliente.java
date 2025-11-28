package com.example.Techmerch.model;

public class Cliente {

    private Integer dniCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String celular;
    private String status;

    public Cliente() {
        // Constructor vacío
    }

    public Cliente(Integer dniCliente, String nombre, String apellido, String direccion,
                   String correo, String celular, String status) {
        this.dniCliente = dniCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
        this.status = status;
    }

    // Getters y Setters
    public Integer getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Integer dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}