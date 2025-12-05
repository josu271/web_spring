package com.example.Techmerch.citatecnica;
import com.example.Techmerch.model.CitaTecnica;
import com.example.Techmerch.model.Cliente;
import com.example.Techmerch.model.Empleado;

import java.util.List;

public interface CitaTecnicaDAO {
    List<CitaTecnica> listaCitasTecnicas();
    CitaTecnica obtenerCitaTecnicaPorId(int id);
    void crearCitaTecnica(CitaTecnica citaTecnica);
    void actualizarCitaTecnica(CitaTecnica citaTecnica);
    void cambiarEstadoCita(int id, String estado);
    void softDeleteCita(int id);

    List<Cliente> obtenerClientesActivos();
    List<Empleado> obtenerTecnicosActivos();
}