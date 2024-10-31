package com.proyecto.proyectofinal.controlador;

import org.springframework.ui.Model;
import com.proyecto.proyectofinal.modelo.Empleado;
import com.proyecto.proyectofinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/listaEmpleados")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        return "listaEmpleados";
    }

    @PostMapping
    public String crearEmpleado(@ModelAttribute Empleado empleado, Model model) {
        if (empleado.getId() != 0 && empleadoRepository.existsById(empleado.getId())) {
            model.addAttribute("errorMessage", "El ID del empleado ya existe. No se puede registrar un empleado con un ID duplicado.");
            return "register";
        }
        empleadoRepository.save(empleado);
        return "redirect:/empleados/listaEmpleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            return "register";
        } else {
            return "redirect:/empleados/listaEmpleados";
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "register";
    }

    @GetMapping("/buscar")
    public String buscarEmpleadoPorId(@RequestParam("id") Long id, Model model) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
        } else {
            model.addAttribute("empleado", new Empleado());
            model.addAttribute("errorMessage", "Empleado no encontrado");
        }
        return "register";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute Empleado empleadoActualizado) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            empleado.setNombreEmpleado(empleadoActualizado.getNombreEmpleado());
            empleado.setApellidoEmpleado(empleadoActualizado.getApellidoEmpleado());
            empleado.setEdad_empleado(empleadoActualizado.getEdad_empleado());
            empleado.setTelefono_empleado(empleadoActualizado.getTelefono_empleado());
            empleado.setFecha_nacimiento_empleado(empleadoActualizado.getFecha_nacimiento_empleado());
            empleadoRepository.save(empleado);
        }
        return "redirect:/empleados/listaEmpleados";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoRepository.deleteById(id);
        return "redirect:/empleados/listaEmpleados";
    }
}
