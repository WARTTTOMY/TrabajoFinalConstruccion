package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.Cita;
import com.proyecto.proyectofinal.modelo.Empleado;
import com.proyecto.proyectofinal.modelo.Habitacion;
import com.proyecto.proyectofinal.modelo.Paciente;
import com.proyecto.proyectofinal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BienvenidaControler {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/")
    public String bienvenida(){
        return "index";
    }

    @GetMapping("/quienesSomos")
    public String quienesSomos(){
        return "quienesSomos";
    }

    @GetMapping("/reportes")
    public String listarReportes(Model model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        System.out.println("Pacientes encontrados: " + pacientes.size());
        model.addAttribute("pacientes", pacientes);

        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        model.addAttribute("articulos", articuloRepository.findAll());

        List<Cita> citas = citaRepository.findAll();
        model.addAttribute("citas", citas);

        List<Habitacion> habitaciones = habitacionRepository.findAll();
        model.addAttribute("habitaciones", habitaciones);

        return "reportes";
    }
}