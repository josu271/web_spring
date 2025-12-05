package com.example.Techmerch.venta;
import com.example.Techmerch.model.DetalleVenta;
import com.example.Techmerch.model.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> findAll();
    Venta findById(Integer id);
    void save(Venta venta);
    Integer saveWithDetails(Venta venta, List<DetalleVenta> detalles);
    void update(Venta venta);
    void delete(Integer id);
}