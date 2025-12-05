package com.example.Techmerch.venta;

import com.example.Techmerch.model.Venta;
import com.example.Techmerch.model.DetalleVenta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaDAO ventaDAO;
    private final DetalleVentaService detalleVentaService;

    public VentaServiceImpl(VentaDAO ventaDAO, DetalleVentaService detalleVentaService) {
        this.ventaDAO = ventaDAO;
        this.detalleVentaService = detalleVentaService;
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
    @Transactional
    public Integer saveWithDetails(Venta venta, List<DetalleVenta> detalles) {
        // 1. Guardar la venta y obtener el ID generado
        Integer idVenta = ventaDAO.saveAndReturnId(venta);

        // 2. Asignar el ID de venta a cada detalle y guardarlos
        for (DetalleVenta detalle : detalles) {
            detalle.setIdVenta(idVenta);
            detalleVentaService.save(detalle);
        }

        return idVenta;
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