package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.modelo.SalidasPacientes;
import com.proyecto.proyectofinal.modelo.Servicio;
import com.proyecto.proyectofinal.repository.PacienteRepository;
import com.proyecto.proyectofinal.repository.SalidasPacientesRepository;
import com.proyecto.proyectofinal.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/salidasPacientes")
public class SalidasPacientesControlador {

    @Autowired
    private SalidasPacientesRepository salidasPacientesRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/registrar")
    public String showRegistrarSalidaForm(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "registrarSalida";
    }

    @PostMapping("/registrar")
    public String registrarSalida(@RequestParam Long pacienteId, Model model) {
        Integer pacienteIdInt = pacienteId.intValue();
        Paciente paciente = pacienteRepository.findById(pacienteIdInt).orElse(null);
        if (paciente == null) {
            model.addAttribute("errorMessage", "Paciente no encontrado");
            return "error";
        }

        List<Servicio> serviciosUtilizados = servicioRepository.findAllByPacientes_Id(Long.valueOf(pacienteIdInt));
        double montoTotal = 0;
        for (Servicio servicio : serviciosUtilizados) {
            montoTotal += servicio.getPrecio();
        }

        SalidasPacientes salida = new SalidasPacientes();
        salida.setPaciente(paciente);
        salida.setFechaSalida(LocalDate.now());
        salida.setMontoTotal(montoTotal);
        salida.setServiciosUtilizados(serviciosUtilizados);

        salidasPacientesRepository.save(salida);

        model.addAttribute("message", "Salida registrada exitosamente");
        return "resultado";
    }

    @GetMapping
    public List<SalidasPacientes> listarSalidasPacientes() {
        return salidasPacientesRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<SalidasPacientes> crearSalidaPaciente(@RequestBody SalidasPacientes salidaPaciente) {
        SalidasPacientes nuevaSalidaPaciente = salidasPacientesRepository.save(salidaPaciente);
        return new ResponseEntity<>(nuevaSalidaPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidasPacientes> obtenerSalidaPacientePorId(@PathVariable Long id) {
        Optional<SalidasPacientes> salidaPaciente = salidasPacientesRepository.findById(id);
        return salidaPaciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidasPacientes> actualizarSalidaPaciente(@PathVariable Long id, @RequestBody SalidasPacientes detallesSalidaPaciente) {
        Optional<SalidasPacientes> salidaPacienteOptional = salidasPacientesRepository.findById(id);
        if (salidaPacienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SalidasPacientes salidaPaciente = salidaPacienteOptional.get();
        salidaPaciente.setPaciente(detallesSalidaPaciente.getPaciente());
        salidaPaciente.setFechaSalida(detallesSalidaPaciente.getFechaSalida());
        salidaPaciente.setMontoTotal(detallesSalidaPaciente.getMontoTotal());

        SalidasPacientes salidaPacienteActualizada = salidasPacientesRepository.save(salidaPaciente);
        return ResponseEntity.ok(salidaPacienteActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalidaPaciente(@PathVariable Long id) {
        if (!salidasPacientesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        salidasPacientesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
