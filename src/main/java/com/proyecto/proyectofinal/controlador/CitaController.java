package com.proyecto.proyectofinal.controlador;

import org.springframework.ui.Model;
import com.proyecto.proyectofinal.modelo.Cita;
import com.proyecto.proyectofinal.modelo.Empleado;
import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.repository.CitaRepository;
import com.proyecto.proyectofinal.repository.EmpleadoRepository;
import com.proyecto.proyectofinal.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping("/empleados")
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/pacientes")
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    /*@GetMapping("/listaCitas")
    public String listaCita(Model model){
        List<Cita> citas = citaRepository.findAll();
        model.addAttribute("citas", citas);
        return "listaCitas";
    }*/

    /*
    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaRepository.save(cita);
    }*/

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        if (cita.getFuncionario() == null || cita.getFuncionario().getId() == 0) {
            throw new RuntimeException("Empleado no proporcionado o inválido");
        }

        Empleado empleado = empleadoRepository.findById(cita.getFuncionario().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        cita.setFuncionario(empleado);
        return citaRepository.save(cita);
    }
}
