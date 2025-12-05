    package com.example.Techmerch.venta;

    import com.example.Techmerch.model.Venta;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.jdbc.core.RowMapper;
    import org.springframework.jdbc.support.GeneratedKeyHolder;
    import org.springframework.jdbc.support.KeyHolder;
    import org.springframework.stereotype.Repository;

    import java.sql.*;
    import java.time.LocalDateTime;
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
        public Integer saveAndReturnId(Venta venta) {
            // SOLUCIÓN: Usar SimpleJdbcInsert que maneja automáticamente las claves generadas
            String sql = "INSERT INTO Ventas (DNI_Cliente, DNI_Empleado, Fecha_Venta, Metodo_Pago, Total, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                // Especificar que queremos obtener el ID_Ventas generado
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_Ventas"});
                ps.setInt(1, venta.getDniCliente());
                ps.setInt(2, venta.getDniEmpleado());
                ps.setTimestamp(3, Timestamp.valueOf(venta.getFechaVenta()));
                ps.setString(4, venta.getMetodoPago());
                ps.setBigDecimal(5, venta.getTotal());
                ps.setString(6, venta.getStatus() != null ? venta.getStatus() : "COMPLETADA");
                return ps;
            }, keyHolder);

            // Obtener el ID generado
            Number key = keyHolder.getKey();
            if (key != null) {
                return key.intValue();
            } else {
                // Fallback: usar MAX(ID_Ventas)
                return jdbcTemplate.queryForObject(
                        "SELECT MAX(ID_Ventas) FROM Ventas", Integer.class
                );
            }
        }

        @Override
        public void save(Venta venta) {
            String sql = "INSERT INTO Ventas (DNI_Cliente, DNI_Empleado, Fecha_Venta, Metodo_Pago, Total, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    venta.getDniCliente(),
                    venta.getDniEmpleado(),
                    Timestamp.valueOf(venta.getFechaVenta()),
                    venta.getMetodoPago(),
                    venta.getTotal(),
                    venta.getStatus() != null ? venta.getStatus() : "COMPLETADA"
            );
        }

        @Override
        public void update(Venta venta) {
            String sql = "UPDATE Ventas SET DNI_Cliente = ?, DNI_Empleado = ?, Metodo_Pago = ?, Total = ?, Status = ? WHERE ID_Ventas = ?";
            jdbcTemplate.update(sql,
                    venta.getDniCliente(),
                    venta.getDniEmpleado(),
                    venta.getMetodoPago(),
                    venta.getTotal(),
                    venta.getStatus(),
                    venta.getIdVenta()
            );
        }

        @Override
        public void delete(Integer id) {
            String sql = "UPDATE Ventas SET Status = 'ANULADA' WHERE ID_Ventas = ?";
            jdbcTemplate.update(sql, id);
        }

        // Método alternativo usando SimpleJdbcInsert
        public Integer saveWithSimpleJdbcInsert(Venta venta) {
            org.springframework.jdbc.core.simple.SimpleJdbcInsert simpleJdbcInsert =
                    new org.springframework.jdbc.core.simple.SimpleJdbcInsert(jdbcTemplate)
                            .withTableName("Ventas")
                            .usingGeneratedKeyColumns("ID_Ventas");

            java.util.Map<String, Object> parameters = new java.util.HashMap<>();
            parameters.put("DNI_Cliente", venta.getDniCliente());
            parameters.put("DNI_Empleado", venta.getDniEmpleado());
            parameters.put("Fecha_Venta", Timestamp.valueOf(venta.getFechaVenta()));
            parameters.put("Metodo_Pago", venta.getMetodoPago());
            parameters.put("Total", venta.getTotal());
            parameters.put("Status", venta.getStatus() != null ? venta.getStatus() : "COMPLETADA");

            Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
            return id.intValue();
        }
    }