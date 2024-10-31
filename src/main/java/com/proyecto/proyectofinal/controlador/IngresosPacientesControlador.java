package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.IngresosPacientes;
import com.proyecto.proyectofinal.servicio.IngresosPacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngresosPacientesControlador {

    @Autowired
    private IngresosPacientesService ingresosPacientesService;

    @GetMapping("/ingresar_paciente")
    public String showIngresarPacienteForm(Model model) {
        model.addAttribute("ingresoPaciente", new IngresosPacientes());
        return "ingresar_paciente";
    }

    @PostMapping("/ingresar_paciente")
    public String ingresarPaciente(@ModelAttribute IngresosPacientes ingresoPaciente, Model model) {
        Long pacienteId = Long.valueOf(ingresoPaciente.getPaciente() != null ? ingresoPaciente.getPaciente().getId() : null);

        try {
            ingresosPacientesService.ingresarPaciente(
                    pacienteId,
                    ingresoPaciente.getCiudad(),
                    ingresoPaciente.getMotivo(),
                    ingresoPaciente.isTieneAcompañante()
            );
            model.addAttribute("message", "Paciente ingresado exitosamente");

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Paciente no encontrado");
            model.addAttribute("ingresoPaciente", ingresoPaciente); // Asegúrate de agregar el objeto al modelo en caso de error
            return "ingresar_paciente";
        }

        return "resultado";
    }
}