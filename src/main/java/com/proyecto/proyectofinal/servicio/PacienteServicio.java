package com.proyecto.proyectofinal.servicio;

import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServicio implements IPacienteServicio {


    @Autowired
    private PacienteRepository pacienteRepository;


    @Override
    public List<Paciente> listarPacientes() {
        return (List<Paciente>)pacienteRepository.findAll();
    }
}
