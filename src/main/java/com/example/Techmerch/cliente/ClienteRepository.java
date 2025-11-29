package com.example.Techmerch.cliente;
import com.example.Techmerch.model.Cliente;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository implements ClienteDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cliente> clienteRowMapper = (rs, rowNum) -> {
        return new Cliente(
                rs.getInt("DNI_Cliente"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getString("Direccion"),
                rs.getString("Correo"),
                rs.getString("Celular"),
                rs.getString("Status")
        );
    };

    public List<Cliente> listaClientes() {
        String query = "SELECT DNI_Cliente, Nombre, Apellido, Direccion, Correo, Celular, Status FROM Cliente WHERE Status != 'ELIMINADO'";
        return jdbcTemplate.query(query, clienteRowMapper);
    }

    public Cliente obtenerClientePorDni(Integer dni) {
        String query = "SELECT DNI_Cliente, Nombre, Apellido, Direccion, Correo, Celular, Status FROM Cliente WHERE DNI_Cliente = ?";
        List<Cliente> result = jdbcTemplate.query(query, clienteRowMapper, dni);
        if (result.isEmpty()) {
            return null;
        } else if (result.size() == 1) {
            return result.get(0);
        } else {
            throw new IllegalStateException("Expected 0 or 1 row but found " + result.size() + " for dni " + dni);
        }
    }

    public void crearCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (DNI_Cliente, Nombre, Apellido, Direccion, Correo, Celular, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                cliente.getDniCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDireccion(),
                cliente.getCorreo(),
                cliente.getCelular(),
                cliente.getStatus() != null ? cliente.getStatus() : "ACTIVO");
    }

    public void actualizarCliente(Cliente cliente) {
        String query = "UPDATE Cliente SET Nombre = ?, Apellido = ?, Direccion = ?, Correo = ?, Celular = ?, Status = ? WHERE DNI_Cliente = ?";
        jdbcTemplate.update(query,
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDireccion(),
                cliente.getCorreo(),
                cliente.getCelular(),
                cliente.getStatus(),
                cliente.getDniCliente());
    }


    public void cambiarEstadoCliente(Integer dni, String status) {
        String query = "UPDATE Cliente SET Status = ? WHERE DNI_Cliente = ?";
        jdbcTemplate.update(query, status, dni);
    }
    @Override
    public List<Cliente> buscarClientes(String busqueda) {
        String sql = "SELECT DNI_Cliente, Nombre, Apellido, Direccion, Correo, Celular, Status " +
                "FROM Cliente WHERE (Nombre LIKE ? OR Apellido LIKE ?) AND Status = 'ACTIVO'";
        String likeBusqueda = "%" + busqueda + "%";
        return jdbcTemplate.query(sql, clienteRowMapper, likeBusqueda, likeBusqueda);
    }
}