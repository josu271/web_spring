package com.example.Techmerch.venta;

import com.example.Techmerch.model.Venta;
import java.util.List;

public interface VentaDAO {
    List<Venta> findAll();
    Venta findById(Integer id);
    void save(Venta venta);
    Integer saveAndReturnId(Venta venta);
    void update(Venta venta);
    void delete(Integer id);

}