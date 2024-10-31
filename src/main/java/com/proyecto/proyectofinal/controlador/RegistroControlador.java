package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.*;
import com.proyecto.proyectofinal.repository.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroControlador {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ServicioRepository servicioRepository;



    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // Nombre de la vista a mostrar
    }


//  REGISTRAR PACIENTES
    @GetMapping("/registerPatient")
    public String showRegisterPatientPage() {
        return "registerPatient"; // Vista a mostrar
    }

    @PostMapping("/registerPatient")
    public String handleRegisterPatient(@ModelAttribute Paciente paciente, Model model) {
        try {
            Paciente nuevoPaciente = pacienteRepository.save(paciente);
            model.addAttribute("paciente", nuevoPaciente);
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el paciente: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace en la consola para ver detalles
            return "/registerPatient"; // Volver a la vista de registro en caso de error
        }
        return "redirect:/index";
    }


//  REGISTRAR EMPLEADOS

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Empleado empleado) {
        if (empleado.getId() == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        // Guardar empleado
        empleadoRepository.save(empleado);
        return "redirect:/";
    }

    // Registrar habitaciones
    @GetMapping("/registerHabitacion")
    public String showRegisterHabitacionPage(Model model) {
        model.addAttribute("habitacion", new Habitacion()); // Asegúrate de que tu clase Habitacion tenga un constructor por defecto
        return "registerHabitacion"; // Vista de registro de habitaciones
    }

    @PostMapping("/registerHabitacion")
    public String handleRegisterHabitacin(@ModelAttribute Habitacion habitacion, Model model) {
        try {
            Habitacion habitacion1 = habitacionRepository.save(habitacion);
            model.addAttribute("paciente", habitacion1);
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el paciente: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace en la consola para ver detalles
            return "/registerPatient"; // Volver a la vista de registro en caso de error
        }
        return "redirect:/registerPatient";
    }

    //Regidstrar articulo
    @GetMapping("/ingresarArticulo")
    public String showIngresarArticuloForm(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "ingresarArticulo";
    }

    @PostMapping("/ingresarArticulo")
    public String ingresarArticulo(@ModelAttribute Articulo articulo, Model model) {
        try {
            // Check if the habitacion exists
            if (!habitacionRepository.existsById(articulo.getHabitacion().getId())) {
                model.addAttribute("errorMessage", "Habitacion no encontrada");
                return "ingresarArticulo";
            }
            articuloRepository.save(articulo);
            model.addAttribute("message", "Artículo registrado exitosamente");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al guardar el artículo: " + e.getMessage());
            model.addAttribute("articulo", articulo); // Ensure the object is added to the model in case of error
            return "ingresarArticulo";
        }
        return "resultado";
    }

    //Servicio
    @GetMapping("/ingresarServicio")
    public String showIngresarServicioForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "asignarServicios\"";
    }

    @PostMapping("/ingresarServicio")
    public String ingresarServicio(@ModelAttribute Servicio servicio, Model model) {
        try {
            servicioRepository.save(servicio);
            model.addAttribute("message", "Servicio registrado exitosamente");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al guardar el servicio: " + e.getMessage());
            model.addAttribute("servicio", servicio); // Asegúrate de agregar el objeto al modelo en caso de error
            return "asignarServicios\"";
        }
        return "resultado";
    }


}
