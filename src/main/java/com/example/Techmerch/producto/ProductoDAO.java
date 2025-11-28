package com.example.Techmerch.producto;

import com.example.Techmerch.model.Producto;
import java.util.List;

public interface ProductoDAO {
    List<Producto> findAll();
    Producto findById(Integer id);
    void save(Producto producto);
    void update(Producto producto);
    void deleteById(Integer id);
    void updateStatus(Integer id, String status);
}