package com.example.Techmerch.producto;

import com.example.Techmerch.producto.ProductoDAO;
import com.example.Techmerch.model.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductoRepository implements ProductoDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Producto> productoRowMapper = (rs, rowNum) -> {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("ID_Producto"));
        producto.setIdCategoria(rs.getInt("ID_Categoria"));
        producto.setNombre(rs.getString("Nombre"));
        producto.setDescripcion(rs.getString("Descripcion"));
        producto.setTipoProducto(rs.getString("Tipo_Producto"));
        producto.setPrecio(rs.getBigDecimal("Precio"));
        producto.setStock(rs.getInt("Stock"));
        producto.setStatus(rs.getString("Status"));
        return producto;
    };

    @Override
    public List<Producto> findAll() {
        String sql = "SELECT p.*, c.Nombre as CategoriaNombre FROM Producto p " +
                "LEFT JOIN Categorias c ON p.ID_Categoria = c.ID_Categoria " +
                "WHERE p.Status != 'ELIMINADO' ORDER BY p.ID_Producto";
        return jdbcTemplate.query(sql, productoRowMapper);
    }

    @Override
    public Producto findById(Integer id) {
        String sql = "SELECT * FROM Producto WHERE ID_Producto = ? AND Status != 'ELIMINADO'";
        List<Producto> productos = jdbcTemplate.query(sql, productoRowMapper, id);
        return productos.isEmpty() ? null : productos.get(0);
    }

    @Override
    public void save(Producto producto) {
        String sql = "INSERT INTO Producto (ID_Categoria, Nombre, Descripcion, Tipo_Producto, Precio, Stock, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'ACTIVO')";
        jdbcTemplate.update(sql, producto.getIdCategoria(), producto.getNombre(),
                producto.getDescripcion(), producto.getTipoProducto(),
                producto.getPrecio(), producto.getStock());
    }

    @Override
    public void update(Producto producto) {
        String sql = "UPDATE Producto SET ID_Categoria = ?, Nombre = ?, Descripcion = ?, " +
                "Tipo_Producto = ?, Precio = ?, Stock = ?, Status = ? WHERE ID_Producto = ?";
        jdbcTemplate.update(sql, producto.getIdCategoria(), producto.getNombre(),
                producto.getDescripcion(), producto.getTipoProducto(),
                producto.getPrecio(), producto.getStock(), producto.getStatus(),
                producto.getIdProducto());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "UPDATE Producto SET Status = 'ELIMINADO' WHERE ID_Producto = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateStatus(Integer id, String status) {
        String sql = "UPDATE Producto SET Status = ? WHERE ID_Producto = ?";
        jdbcTemplate.update(sql, status, id);
    }
    @Override
    public List<Producto> buscarProductos(String busqueda) {
        String sql = "SELECT * FROM Producto WHERE Nombre LIKE ? AND Status = 'ACTIVO' AND Stock > 0";
        String likeBusqueda = "%" + busqueda + "%";
        return jdbcTemplate.query(sql, productoRowMapper, likeBusqueda);
    }
}