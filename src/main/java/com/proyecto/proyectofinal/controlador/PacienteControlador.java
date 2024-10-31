package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.proyectofinal.modelo.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteControlador {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/listaPacientes")
    public String listarPacientes(Model model){
        List<Paciente> pacientes = pacienteRepository.findAll();
        System.out.println("Pacientes encontrados: " + pacientes.size());
        model.addAttribute("pacientes", pacientes);
        return "listaPacientes";
    }

    @PostMapping
    public String creaPaciente(@ModelAttribute Paciente paciente, Model model) {
        if (paciente.getId() != 0 && pacienteRepository.existsById(paciente.getId())) {
            model.addAttribute("errorMessage", "El ID del paciente ya existe. No se puede registrar un paciente con un ID duplicado.");
            return "registerPatient";
        }
        pacienteRepository.save(paciente);
        return "redirect:/pacientes/listaPacientes";
    }

    @GetMapping("/buscar")
    public String buscarPacientePorId(@RequestParam("id") int id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
        } else {
            model.addAttribute("errorMessage", "Paciente no encontrado");
        }
        return "registerPatient";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable int id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
            return "registerPatient";
        } else {
            return "redirect:/pacientes/listaPacientes";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPaciente(@PathVariable int id, @ModelAttribute Paciente pacienteActualizado) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            paciente.setNomPaciente(pacienteActualizado.getNomPaciente());
            paciente.setApePaciente(pacienteActualizado.getApePaciente());
            paciente.setEdad_paciente(pacienteActualizado.getEdad_paciente());
            paciente.setDireccion_paciente(pacienteActualizado.getDireccion_paciente());
            paciente.setTelefono_paciente(pacienteActualizado.getTelefono_paciente());
            paciente.setEps_paciente(pacienteActualizado.getEps_paciente());
            pacienteRepository.save(paciente);
        }
        return "redirect:/pacientes/listaPacientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable int id) {
        pacienteRepository.deleteById(id);
        return "redirect:/pacientes/listaPacientes";
    }
}
