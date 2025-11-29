package com.example.Techmerch.venta;

import com.example.Techmerch.model.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaDAO ventaDAO;

    public VentaServiceImpl(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    @Override
    public List<Venta> findAll() {
        return ventaDAO.findAll();
    }

    @Override
    public Venta findById(Integer id) {
        return ventaDAO.findById(id);
    }

    @Override
    public void save(Venta venta) {
        ventaDAO.save(venta);
    }

    @Override
    public void update(Venta venta) {
        ventaDAO.update(venta);
    }

    @Override
    public void delete(Integer id) {
        ventaDAO.delete(id);
    }
}