package com.example.Techmerch.empleado;

import com.example.Techmerch.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoDAO empleadoDAO;

    public EmpleadoServiceImpl(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoDAO.findAll();
    }

    @Override
    public Empleado findById(Integer dni) {
        return empleadoDAO.findById(dni);
    }

    @Override
    public void save(Empleado empleado) {
        empleadoDAO.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
        empleadoDAO.update(empleado);
    }

    @Override
    public void delete(Integer dni) {
        empleadoDAO.delete(dni);
    }

    @Override
    public Empleado login(Integer dni, String password) {
        return empleadoDAO.findByDniAndPassword(dni, password);
    }

    @Override
    public List<Empleado> findByStatus(String status) {
        return empleadoDAO.findByStatus(status);
    }
}