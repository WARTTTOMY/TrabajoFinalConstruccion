package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.modelo.PacienteServicio;
import com.proyecto.proyectofinal.modelo.Servicio;
import com.proyecto.proyectofinal.repository.PacienteRepository;
import com.proyecto.proyectofinal.repository.PacienteServicioRepository;
import com.proyecto.proyectofinal.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioControlador {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteServicioRepository pacienteServicioRepository;

    @GetMapping("/asignar")
    public String mostrarFormularioAsignarServicios(Model model) {
        model.addAttribute("servicios", servicioRepository.findAll());
        return "asignarServicios";
    }

    @PostMapping("/asignar")
    public String asignarServicios(@RequestParam int pacienteId, @RequestParam List<Integer> servicioIds, Model model) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        if (paciente == null) {
            model.addAttribute("errorMessage", "Paciente no encontrado");
            return "error";
        }
        List<Servicio> servicios = servicioRepository.findAllById(servicioIds);
        for (Servicio servicio : servicios) {
            PacienteServicio pacienteServicio = new PacienteServicio();
            pacienteServicio.setPaciente(paciente);
            pacienteServicio.setServicio(servicio);
            pacienteServicio.setPrecio(servicio.getPrecio());
            pacienteServicioRepository.save(pacienteServicio);
        }
        model.addAttribute("message", "Servicios asignados exitosamente");
        return "resultado";
    }

    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @PutMapping("/{id}")
    public Servicio actualizarServicio(@PathVariable int id, @RequestBody Servicio detallesServicio) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            return null;
        }
        servicio.setId(detallesServicio.getId());
        servicio.setNombre(detallesServicio.getNombre());
        servicio.setPrecio(detallesServicio.getPrecio());
        servicio.setDetalle(detallesServicio.getDetalle());

        return servicioRepository.save(servicio);
    }

    @DeleteMapping("/{id}")
    public void eliminarServicio(@PathVariable int id) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow();
        servicioRepository.deleteById(id);
    }
}