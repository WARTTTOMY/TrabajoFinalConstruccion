package com.proyecto.proyectofinal.servicio;

import com.proyecto.proyectofinal.modelo.IngresosPacientes;
import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.repository.IngresosPacientesRepository;
import com.proyecto.proyectofinal.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresosPacientesService {

    @Autowired
    private IngresosPacientesRepository ingresosPacientesRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public IngresosPacientes ingresarPaciente(Long pacienteId, String ciudad, String motivo, boolean tieneAcompanante) {
        Paciente paciente = pacienteRepository.findById(Math.toIntExact(pacienteId))
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        IngresosPacientes ingreso = new IngresosPacientes();
        ingreso.setPaciente(paciente);
        ingreso.setCiudad(ciudad);
        ingreso.setMotivo(motivo);
        ingreso.setTieneAcompañante(tieneAcompanante);

        return ingresosPacientesRepository.save(ingreso);

    }


}
