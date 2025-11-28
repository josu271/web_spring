package com.example.Techmerch.producto;

import com.example.Techmerch.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodosProductos();
    Producto obtenerProductoPorId(Integer id);
    void crearProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Integer id);
    void activarProducto(Integer id);
    void desactivarProducto(Integer id);
}