package com.example.Techmerch.model;

public class Empleado {
    private Integer dniEmpleado;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String celular;
    private String cargo;
    private String contra;
    private String status;

    public Empleado() {
    }

    public Empleado(Integer dniEmpleado, String nombre, String apellido, String direccion,
                    String correo, String celular, String cargo, String contra, String status) {
        this.dniEmpleado = dniEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
        this.cargo = cargo;
        this.contra = contra;  // Inicializado en el constructor
        this.status = status;
    }

    // Getters y Setters
    public Integer getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Integer dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Getter y Setter para contra
    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "dniEmpleado=" + dniEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", celular='" + celular + '\'' +
                ", cargo='" + cargo + '\'' +
                ", contra='" + contra + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}