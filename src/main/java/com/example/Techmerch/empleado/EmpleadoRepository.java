package com.example.Techmerch.empleado;

import com.example.Techmerch.model.Empleado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpleadoRepository implements EmpleadoDAO {

    private final JdbcTemplate jdbcTemplate;

    public EmpleadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Empleado> empleadoRowMapper = (rs, rowNum) -> {
        return new Empleado(
                rs.getInt("DNI_Empleado"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getString("Direccion"),
                rs.getString("Correo"),
                rs.getString("Celular"),
                rs.getString("Cargo"),
                rs.getString("Status"),
                rs.getString("contra")
        );
    };

    @Override
    public List<Empleado> findAll() {
        String query = "SELECT * FROM Empleado WHERE Status != 'ELIMINADO'";
        return jdbcTemplate.query(query, empleadoRowMapper);
    }

    @Override
    public Empleado findById(Integer dni) {
        String query = "SELECT * FROM Empleado WHERE DNI_Empleado = ? AND Status != 'ELIMINADO'";
        List<Empleado> result = jdbcTemplate.query(query, empleadoRowMapper, dni);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void save(Empleado empleado) {
        String query = "INSERT INTO Empleado (DNI_Empleado, Nombre, Apellido, Direccion, Correo, Celular, Cargo, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                empleado.getDniEmpleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDireccion(),
                empleado.getCorreo(),
                empleado.getCelular(),
                empleado.getCargo(),
                empleado.getStatus() != null ? empleado.getStatus() : "ACTIVO"
        );
    }

    @Override
    public void update(Empleado empleado) {
        String query = "UPDATE Empleado SET Nombre = ?, Apellido = ?, Direccion = ?, Correo = ?, Celular = ?, Cargo = ?, Status = ? WHERE DNI_Empleado = ?";
        jdbcTemplate.update(query,
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDireccion(),
                empleado.getCorreo(),
                empleado.getCelular(),
                empleado.getCargo(),
                empleado.getStatus(),
                empleado.getDniEmpleado()
        );
    }

    @Override
    public void delete(Integer dni) {
        String query = "UPDATE Empleado SET Status = 'ELIMINADO' WHERE DNI_Empleado = ?";
        jdbcTemplate.update(query, dni);
    }

    @Override
    public Empleado findByDniAndPassword(Integer dni, String password) {
        String query = "SELECT * FROM Empleado WHERE DNI_Empleado = ? AND Status = 'ACTIVO'";
        List<Empleado> result = jdbcTemplate.query(query, empleadoRowMapper, dni);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Empleado> findByStatus(String status) {
        String query = "SELECT * FROM Empleado WHERE Status = ?";
        return jdbcTemplate.query(query, empleadoRowMapper, status);
    }
}