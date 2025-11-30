package com.example.Techmerch.reporte;

import com.example.Techmerch.model.Reporte;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ReporteRepository implements ReporteDAO {

    private final JdbcTemplate jdbcTemplate;

    public ReporteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reporte> reporteRowMapper = new RowMapper<Reporte>() {
        @Override
        public Reporte mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reporte reporte = new Reporte();

            // Mapeo flexible para diferentes consultas
            try {
                if (rs.getDate("fecha") != null) {
                    reporte.setFecha(rs.getDate("fecha").toLocalDate());
                }
            } catch (SQLException e) {
                // Ignorar si la columna no existe
            }

            try {
                reporte.setTotalVentas(rs.getBigDecimal("total_ventas"));
            } catch (SQLException e) {
                reporte.setTotalVentas(BigDecimal.ZERO);
            }

            try {
                reporte.setCantidadVentas(rs.getInt("cantidad_ventas"));
            } catch (SQLException e) {
                reporte.setCantidadVentas(0);
            }

            try {
                reporte.setNombreProducto(rs.getString("nombre_producto"));
            } catch (SQLException e) {
                reporte.setNombreProducto(null);
            }

            try {
                reporte.setCantidadVendida(rs.getInt("cantidad_vendida"));
            } catch (SQLException e) {
                reporte.setCantidadVendida(0);
            }

            try {
                reporte.setNombreCategoria(rs.getString("nombre_categoria"));
            } catch (SQLException e) {
                reporte.setNombreCategoria(null);
            }

            try {
                reporte.setPorcentaje(rs.getBigDecimal("porcentaje"));
            } catch (SQLException e) {
                reporte.setPorcentaje(BigDecimal.ZERO);
            }

            try {
                reporte.setPromedioVenta(rs.getBigDecimal("promedio_venta"));
            } catch (SQLException e) {
                reporte.setPromedioVenta(BigDecimal.ZERO);
            }

            return reporte;
        }
    };

    @Override
    public List<Reporte> obtenerVentasPorPeriodo(String periodo) {
        String query = "";
        switch (periodo.toLowerCase()) {
            case "dia":
                query = "SELECT CAST(Fecha_Venta AS DATE) as fecha, SUM(Total) as total_ventas, COUNT(*) as cantidad_ventas " +
                        "FROM Ventas WHERE CAST(Fecha_Venta AS DATE) = CURRENT_DATE() AND Status = 'COMPLETADA' " +
                        "GROUP BY CAST(Fecha_Venta AS DATE) ORDER BY fecha";
                break;
            case "semana":
                // H2 no tiene función WEEK(), usamos una alternativa
                query = "SELECT CAST(Fecha_Venta AS DATE) as fecha, SUM(Total) as total_ventas, COUNT(*) as cantidad_ventas " +
                        "FROM Ventas WHERE Fecha_Venta >= CURRENT_DATE() - 7 AND Status = 'COMPLETADA' " +
                        "GROUP BY CAST(Fecha_Venta AS DATE) ORDER BY fecha";
                break;
            case "mes":
                query = "SELECT CAST(Fecha_Venta AS DATE) as fecha, SUM(Total) as total_ventas, COUNT(*) as cantidad_ventas " +
                        "FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND MONTH(Fecha_Venta) = MONTH(CURRENT_DATE()) AND Status = 'COMPLETADA' " +
                        "GROUP BY CAST(Fecha_Venta AS DATE) ORDER BY fecha";
                break;
            case "año":
                query = "SELECT MONTH(Fecha_Venta) as fecha, SUM(Total) as total_ventas, COUNT(*) as cantidad_ventas " +
                        "FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND Status = 'COMPLETADA' " +
                        "GROUP BY MONTH(Fecha_Venta) ORDER BY fecha";
                break;
            default:
                query = "SELECT CAST(Fecha_Venta AS DATE) as fecha, SUM(Total) as total_ventas, COUNT(*) as cantidad_ventas " +
                        "FROM Ventas WHERE Status = 'COMPLETADA' " +
                        "GROUP BY CAST(Fecha_Venta AS DATE) ORDER BY fecha DESC LIMIT 7";
        }

        try {
            System.out.println("Ejecutando query: " + query);
            List<Reporte> result = jdbcTemplate.query(query, reporteRowMapper);
            System.out.println("Resultados obtenidos: " + result.size());
            return result;
        } catch (Exception e) {
            System.err.println("Error en obtenerVentasPorPeriodo: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Reporte> obtenerTopProductos(Integer limite) {
        String query = "SELECT p.Nombre as nombre_producto, SUM(dv.Cantidad) as cantidad_vendida " +
                "FROM DetalleVenta dv " +
                "JOIN Producto p ON dv.ID_Producto = p.ID_Producto " +
                "JOIN Ventas v ON dv.ID_Ventas = v.ID_Ventas " +
                "WHERE v.Status = 'COMPLETADA' AND dv.Status = 'ACTIVO' " +
                "GROUP BY p.ID_Producto, p.Nombre " +
                "ORDER BY cantidad_vendida DESC " +
                "LIMIT ?";

        try {
            System.out.println("Ejecutando top productos, límite: " + limite);
            List<Reporte> result = jdbcTemplate.query(query, reporteRowMapper, limite);
            System.out.println("Top productos obtenidos: " + result.size());
            return result;
        } catch (Exception e) {
            System.err.println("Error en obtenerTopProductos: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Reporte> obtenerCategoriasMasVendidas() {
        // Simplificamos la consulta para evitar subconsultas complejas
        String query = "SELECT c.Nombre as nombre_categoria, " +
                "SUM(dv.Cantidad) as cantidad_vendida " +
                "FROM DetalleVenta dv " +
                "JOIN Producto p ON dv.ID_Producto = p.ID_Producto " +
                "JOIN Categorias c ON p.ID_Categoria = c.ID_Categoria " +
                "JOIN Ventas v ON dv.ID_Ventas = v.ID_Ventas " +
                "WHERE v.Status = 'COMPLETADA' AND dv.Status = 'ACTIVO' " +
                "GROUP BY c.ID_Categoria, c.Nombre " +
                "ORDER BY cantidad_vendida DESC";

        try {
            System.out.println("Ejecutando categorías más vendidas");
            List<Reporte> result = jdbcTemplate.query(query, reporteRowMapper);
            System.out.println("Categorías obtenidas: " + result.size());
            return result;
        } catch (Exception e) {
            System.err.println("Error en obtenerCategoriasMasVendidas: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public BigDecimal obtenerIngresosTotales(String periodo) {
        String query = "";
        switch (periodo.toLowerCase()) {
            case "dia":
                query = "SELECT COALESCE(SUM(Total), 0) FROM Ventas WHERE CAST(Fecha_Venta AS DATE) = CURRENT_DATE() AND Status = 'COMPLETADA'";
                break;
            case "semana":
                query = "SELECT COALESCE(SUM(Total), 0) FROM Ventas WHERE Fecha_Venta >= CURRENT_DATE() - 7 AND Status = 'COMPLETADA'";
                break;
            case "mes":
                query = "SELECT COALESCE(SUM(Total), 0) FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND MONTH(Fecha_Venta) = MONTH(CURRENT_DATE()) AND Status = 'COMPLETADA'";
                break;
            case "año":
                query = "SELECT COALESCE(SUM(Total), 0) FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND Status = 'COMPLETADA'";
                break;
            default:
                query = "SELECT COALESCE(SUM(Total), 0) FROM Ventas WHERE Status = 'COMPLETADA'";
        }

        try {
            System.out.println("Ejecutando ingresos totales: " + periodo);
            BigDecimal result = jdbcTemplate.queryForObject(query, BigDecimal.class);
            System.out.println("Ingresos obtenidos: " + result);
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            System.err.println("Error en obtenerIngresosTotales: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal obtenerPromedioVenta(String periodo) {
        String query = "";
        switch (periodo.toLowerCase()) {
            case "dia":
                query = "SELECT COALESCE(AVG(Total), 0) FROM Ventas WHERE CAST(Fecha_Venta AS DATE) = CURRENT_DATE() AND Status = 'COMPLETADA'";
                break;
            case "semana":
                query = "SELECT COALESCE(AVG(Total), 0) FROM Ventas WHERE Fecha_Venta >= CURRENT_DATE() - 7 AND Status = 'COMPLETADA'";
                break;
            case "mes":
                query = "SELECT COALESCE(AVG(Total), 0) FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND MONTH(Fecha_Venta) = MONTH(CURRENT_DATE()) AND Status = 'COMPLETADA'";
                break;
            case "año":
                query = "SELECT COALESCE(AVG(Total), 0) FROM Ventas WHERE YEAR(Fecha_Venta) = YEAR(CURRENT_DATE()) AND Status = 'COMPLETADA'";
                break;
            default:
                query = "SELECT COALESCE(AVG(Total), 0) FROM Ventas WHERE Status = 'COMPLETADA'";
        }

        try {
            System.out.println("Ejecutando promedio venta: " + periodo);
            BigDecimal result = jdbcTemplate.queryForObject(query, BigDecimal.class);
            System.out.println("Promedio obtenido: " + result);
            return result != null ? result : BigDecimal.ZERO;
        } catch (Exception e) {
            System.err.println("Error en obtenerPromedioVenta: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public Integer obtenerTotalCitasPorEstado(String estado) {
        String query = "SELECT COUNT(*) FROM Cita_Tecnica WHERE Estado = ? AND Status = 'ACTIVO'";
        try {
            System.out.println("Ejecutando citas por estado: " + estado);
            Integer result = jdbcTemplate.queryForObject(query, Integer.class, estado);
            System.out.println("Citas obtenidas: " + result);
            return result != null ? result : 0;
        } catch (Exception e) {
            System.err.println("Error en obtenerTotalCitasPorEstado: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}