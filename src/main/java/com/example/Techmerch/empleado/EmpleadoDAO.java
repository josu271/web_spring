package com.example.Techmerch.empleado;

import com.example.Techmerch.model.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    List<Empleado> findAll();
    Empleado findById(Integer dni);
    void save(Empleado empleado);
    void update(Empleado empleado);
    void delete(Integer dni);
    Empleado findByDniAndPassword(Integer dni, String password);
    List<Empleado> findByStatus(String status);
}