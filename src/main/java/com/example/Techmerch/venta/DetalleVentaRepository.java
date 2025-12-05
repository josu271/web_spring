package com.example.Techmerch.venta;

import com.example.Techmerch.model.DetalleVenta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetalleVentaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DetalleVentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper actualizado para tu estructura de tabla
    private final RowMapper<DetalleVenta> detalleRowMapper = new RowMapper<DetalleVenta>() {
        @Override
        public DetalleVenta mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdDetalle(rs.getInt("ID_Detalle")); // Cambiado de ID_Detalle_Ventas
            detalle.setIdVenta(rs.getInt("ID_Ventas"));
            detalle.setIdProducto(rs.getInt("ID_Producto"));
            detalle.setCantidad(rs.getInt("Cantidad"));
            detalle.setPrecioUnitario(rs.getBigDecimal("Precio_Unitario"));
            detalle.setSubtotal(rs.getBigDecimal("Subtotal"));
            detalle.setStatus(rs.getString("Status"));
            return detalle;
        }
    };

    public void save(DetalleVenta detalle) {
        // CORRECCIÓN: Usar el nombre correcto de la tabla "DetalleVenta" y columna "ID_Detalle"
        String sql = "INSERT INTO DetalleVenta (ID_Ventas, ID_Producto, Cantidad, Precio_Unitario, Subtotal, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                detalle.getIdVenta(),
                detalle.getIdProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal(),
                detalle.getStatus() != null ? detalle.getStatus() : "ACTIVO"
        );
    }

    public void saveAll(List<DetalleVenta> detalles) {
        String sql = "INSERT INTO DetalleVenta (ID_Ventas, ID_Producto, Cantidad, Precio_Unitario, Subtotal, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        for (DetalleVenta detalle : detalles) {
            jdbcTemplate.update(sql,
                    detalle.getIdVenta(),
                    detalle.getIdProducto(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getSubtotal(),
                    detalle.getStatus() != null ? detalle.getStatus() : "ACTIVO"
            );
        }
    }

    public List<DetalleVenta> findByVentaId(Integer idVenta) {
        String sql = "SELECT * FROM DetalleVenta WHERE ID_Ventas = ? AND Status != 'ELIMINADO'";
        return jdbcTemplate.query(sql, detalleRowMapper, idVenta);
    }

    // Método adicional para verificar conexión
    public boolean tablaExiste() {
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DetalleVenta", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}