package com.example.Techmerch.venta;

import com.example.Techmerch.model.DetalleVenta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Transactional
    public void save(DetalleVenta detalle) {
        detalleVentaRepository.save(detalle);
    }

    @Transactional
    public void saveAll(List<DetalleVenta> detalles) {
        for (DetalleVenta detalle : detalles) {
            detalleVentaRepository.save(detalle);
        }
    }

    public List<DetalleVenta> findByVentaId(Integer idVenta) {
        return detalleVentaRepository.findByVentaId(idVenta);
    }
}