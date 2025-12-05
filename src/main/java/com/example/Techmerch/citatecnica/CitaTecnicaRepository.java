package com.example.Techmerch.citatecnica;

import com.example.Techmerch.model.CitaTecnica;
import com.example.Techmerch.model.Cliente;
import com.example.Techmerch.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CitaTecnicaRepository implements CitaTecnicaDAO {

    private final JdbcTemplate jdbcTemplate;

    public CitaTecnicaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CitaTecnica> citaTecnicaRowMapper = (rs, rowNum) -> {
        return new CitaTecnica(
                rs.getInt("ID_CitaTecnica"),
                rs.getInt("DNI_Cliente"),
                rs.getInt("DNI_Empleado"),
                rs.getString("Servicio"),
                rs.getString("Estado"),
                rs.getString("Descripcion"),
                rs.getTimestamp("Fecha_Programada").toLocalDateTime(),
                rs.getString("Status")
        );
    };

    @Override
    public List<CitaTecnica> listaCitasTecnicas() {
        String query = "SELECT * FROM Cita_Tecnica WHERE Status = 'ACTIVO'";
        return jdbcTemplate.query(query, citaTecnicaRowMapper);
    }

    @Override
    public CitaTecnica obtenerCitaTecnicaPorId(int id) {
        String query = "SELECT * FROM Cita_Tecnica WHERE ID_CitaTecnica = ? AND Status = 'ACTIVO'";
        List<CitaTecnica> result = jdbcTemplate.query(query, citaTecnicaRowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void crearCitaTecnica(CitaTecnica citaTecnica) {
        String query = "INSERT INTO Cita_Tecnica (DNI_Cliente, DNI_Empleado, Servicio, Estado, Descripcion, Fecha_Programada, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                citaTecnica.getDniCliente(),
                citaTecnica.getDniEmpleado(),
                citaTecnica.getServicio(),
                citaTecnica.getEstado(),
                citaTecnica.getDescripcion(),
                citaTecnica.getFechaProgramada(),
                citaTecnica.getStatus()
        );
    }

    @Override
    public void actualizarCitaTecnica(CitaTecnica citaTecnica) {
        String query = "UPDATE Cita_Tecnica SET DNI_Cliente = ?, DNI_Empleado = ?, Servicio = ?, Estado = ?, Descripcion = ?, Fecha_Programada = ? WHERE ID_CitaTecnica = ?";
        jdbcTemplate.update(query,
                citaTecnica.getDniCliente(),
                citaTecnica.getDniEmpleado(),
                citaTecnica.getServicio(),
                citaTecnica.getEstado(),
                citaTecnica.getDescripcion(),
                citaTecnica.getFechaProgramada(),
                citaTecnica.getIdCitaTecnica()
        );
    }

    @Override
    public void cambiarEstadoCita(int id, String estado) {
        String query = "UPDATE Cita_Tecnica SET Estado = ? WHERE ID_CitaTecnica = ?";
        jdbcTemplate.update(query, estado, id);
    }

    @Override
    public void softDeleteCita(int id) {
        String query = "UPDATE Cita_Tecnica SET Status = 'INACTIVO' WHERE ID_CitaTecnica = ?";
        jdbcTemplate.update(query, id);
    }

    // Nuevos métodos para obtener clientes y técnicos activos
    public List<Cliente> obtenerClientesActivos() {
        String query = "SELECT * FROM Cliente WHERE Status = 'ACTIVO' ORDER BY Nombre, Apellido";
        return jdbcTemplate.query(query, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(
                        rs.getInt("DNI_Cliente"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Direccion"),
                        rs.getString("Correo"),
                        rs.getString("Celular"),
                        rs.getString("Status")
                );
            }
        });
    }

    public List<Empleado> obtenerTecnicosActivos() {
        String query = "SELECT * FROM Empleado WHERE Status = 'ACTIVO' AND Cargo LIKE '%Técnico%' ORDER BY Nombre, Apellido";
        return jdbcTemplate.query(query, new RowMapper<Empleado>() {
            @Override
            public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Empleado(
                        rs.getInt("DNI_Empleado"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Direccion"),
                        rs.getString("Correo"),
                        rs.getString("Celular"),
                        rs.getString("Cargo"),
                        rs.getString("Contra"),
                        rs.getString("Status")
                );
            }
        });
    }
}