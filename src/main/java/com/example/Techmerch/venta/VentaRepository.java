package com.example.Techmerch.venta;

import com.example.Techmerch.model.Venta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VentaRepository implements VentaDAO {

    private final JdbcTemplate jdbcTemplate;

    public VentaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Venta> ventaRowMapper = (rs, rowNum) -> {
        Venta venta = new Venta();
        venta.setIdVenta(rs.getInt("ID_Ventas"));
        venta.setDniCliente(rs.getInt("DNI_Cliente"));
        venta.setDniEmpleado(rs.getInt("DNI_Empleado"));
        venta.setFechaVenta(rs.getTimestamp("Fecha_Venta").toLocalDateTime());
        venta.setMetodoPago(rs.getString("Metodo_Pago"));
        venta.setTotal(rs.getBigDecimal("Total"));
        venta.setStatus(rs.getString("Status"));
        return venta;
    };

    @Override
    public List<Venta> findAll() {
        String sql = "SELECT * FROM Ventas WHERE Status != 'ELIMINADO' ORDER BY Fecha_Venta DESC";
        return jdbcTemplate.query(sql, ventaRowMapper);
    }

    @Override
    public Venta findById(Integer id) {
        String sql = "SELECT * FROM Ventas WHERE ID_Ventas = ? AND Status != 'ELIMINADO'";
        List<Venta> result = jdbcTemplate.query(sql, ventaRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void save(Venta venta) {
        String sql = "INSERT INTO Ventas (DNI_Cliente, DNI_Empleado, Metodo_Pago, Total, Status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, venta.getDniCliente(), venta.getDniEmpleado(),
                venta.getMetodoPago(), venta.getTotal(),
                venta.getStatus() != null ? venta.getStatus() : "COMPLETADA");
    }

    @Override
    public void update(Venta venta) {
        String sql = "UPDATE Ventas SET DNI_Cliente = ?, DNI_Empleado = ?, Metodo_Pago = ?, Total = ?, Status = ? WHERE ID_Ventas = ?";
        jdbcTemplate.update(sql, venta.getDniCliente(), venta.getDniEmpleado(),
                venta.getMetodoPago(), venta.getTotal(), venta.getStatus(),
                venta.getIdVenta());
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE Ventas SET Status = 'ANULADA' WHERE ID_Ventas = ?";
        jdbcTemplate.update(sql, id);
    }
}