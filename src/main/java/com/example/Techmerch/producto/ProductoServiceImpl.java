package com.example.Techmerch.producto;

import com.example.Techmerch.producto.ProductoDAO;
import com.example.Techmerch.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        return productoDAO.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Integer id) {
        return productoDAO.findById(id);
    }

    @Override
    public void crearProducto(Producto producto) {
        productoDAO.save(producto);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        productoDAO.update(producto);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productoDAO.deleteById(id);
    }

    @Override
    public void activarProducto(Integer id) {
        productoDAO.updateStatus(id, "ACTIVO");
    }

    @Override
    public void desactivarProducto(Integer id) {
        productoDAO.updateStatus(id, "INACTIVO");
    }
}