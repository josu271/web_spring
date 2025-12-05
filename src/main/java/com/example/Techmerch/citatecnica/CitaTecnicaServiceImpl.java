package com.example.Techmerch.citatecnica;

import com.example.Techmerch.model.CitaTecnica;
import com.example.Techmerch.model.Cliente;
import com.example.Techmerch.model.Empleado;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CitaTecnicaServiceImpl implements CitaTecnicaService {

    private final CitaTecnicaDAO citaTecnicaDAO;

    public CitaTecnicaServiceImpl(CitaTecnicaDAO citaTecnicaDAO) {
        this.citaTecnicaDAO = citaTecnicaDAO;
    }

    @Override
    public List<CitaTecnica> listaCitasTecnicas() {
        return citaTecnicaDAO.listaCitasTecnicas();
    }

    @Override
    public CitaTecnica obtenerCitaTecnicaPorId(int id) {
        return citaTecnicaDAO.obtenerCitaTecnicaPorId(id);
    }

    @Override
    public void crearCitaTecnica(CitaTecnica citaTecnica) {
        citaTecnicaDAO.crearCitaTecnica(citaTecnica);
    }

    @Override
    public void actualizarCitaTecnica(CitaTecnica citaTecnica) {
        citaTecnicaDAO.actualizarCitaTecnica(citaTecnica);
    }

    @Override
    public void cambiarEstadoCita(int id, String estado) {
        citaTecnicaDAO.cambiarEstadoCita(id, estado);
    }

    @Override
    public void eliminarCitaTecnica(int id) {
        citaTecnicaDAO.softDeleteCita(id);
    }

    @Override
    public List<Cliente> obtenerClientesActivos() {
        return citaTecnicaDAO.obtenerClientesActivos();
    }

    @Override
    public List<Empleado> obtenerTecnicosActivos() {
        return citaTecnicaDAO.obtenerTecnicosActivos();
    }
}