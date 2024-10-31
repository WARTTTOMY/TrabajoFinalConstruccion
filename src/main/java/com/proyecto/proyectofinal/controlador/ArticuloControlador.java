package com.proyecto.proyectofinal.controlador;
import com.proyecto.proyectofinal.repository.HabitacionRepository;
import com.proyecto.proyectofinal.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.proyectofinal.modelo.Habitacion;
import com.proyecto.proyectofinal.modelo.Articulo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/articulos")
public class ArticuloControlador {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/ingresar")
    public String mostrarFormularioIngresoArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "ingresarArticulo";
    }

    @GetMapping("/listaArticulos")
    public String listarArticulos(Model model) {
        /*List<Articulo> articulos = articuloRepository.findAll();
        System.out.println("Articulos encontrados: " + articulos.size());
        model.addAttribute("articulos", articulos);*/
        model.addAttribute("articulos", articuloRepository.findAll());
        return "listarArticulos";
    }

    @PostMapping
    public String registrarArticulo(@ModelAttribute Articulo articulo, @RequestParam("habitacionId") int habitacionId, Model model) {
        Optional<Habitacion> habitacionOptional = habitacionRepository.findById(habitacionId);
        if (habitacionOptional.isPresent()) {
            articulo.setHabitacion(habitacionOptional.get());
            articuloRepository.save(articulo);
            return "redirect:/articulos/listaArticulos";
        } else {
            model.addAttribute("errorMessage", "Habitación no encontrada");
            model.addAttribute("articulo", articulo);
            return "ingresarArticulo";
        }
    }
    //funciona con este
    /*
    @PostMapping
    public String registrarArticulo(@ModelAttribute Articulo articulo, @RequestParam("habitacionId") int habitacionId) {
        habitacionRepository.findById(habitacionId).ifPresent(articulo::setHabitacion);
        articuloRepository.save(articulo);
        return "redirect:/articulos/listaArticulos";
    }*/

    /*@PostMapping
    public String registrarArticulo(@ModelAttribute Articulo articulo, @RequestParam("habitacionId") int habitacionId, Model model) {
        // Verificar si el ID del artículo ya existe
        if (articulo.getId() != 0 && articuloRepository.existsById(articulo.getId())) {
            model.addAttribute("errorMessage", "El ID del artículo ya existe. No se puede registrar un artículo con un ID duplicado.");
            return "ingresarArticulo"; // Devuelve al formulario con el mensaje de error
        }

        // Buscar la habitación asociada
        Habitacion habitacion = habitacionRepository.findById(habitacionId).orElse(null);
        if (habitacion == null) {
            model.addAttribute("errorMessage", "Habitación no encontrada");
            return "error";
        }

        articulo.setHabitacion(habitacion);
        articuloRepository.save(articulo); // Guardar el artículo
        return "redirect:/articulos/listaArticulos"; // Redirigir a la lista de artículos
    }*/

    @GetMapping("/buscar")
    public String buscarArticuloPorId(@RequestParam("id") int id, Model model) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            model.addAttribute("articulo", articulo.get());
        } else {
            model.addAttribute("articulo", new Articulo());
            model.addAttribute("errorMessage", "Artículo no encontrado");
        }
        return "ingresarArticulo";
    }

    /*@GetMapping("/buscar")
    public String buscarArticuloPorId(@RequestParam("id") int id, Model model) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            model.addAttribute("articulo", articulo.get());
            //return "ingresarArticulo";
        } else {
            model.addAttribute("errorMessage", "Artículo no encontrado");
            //return "ingresarArticulo";
        }
        return "ingresarArticulo";
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticuloPorId(@PathVariable int id, Model model) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        /*if (articulo.isPresent()) {
            model.addAttribute("articulo", articulo.get());
            return "detalleArticulo";
        } else {
            model.addAttribute("errorMessage", "Artículo no encontrado");
            return "error";
        }*/
        return articulo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model model) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            model.addAttribute("articulo", articulo.get());
            return "editarArticulo";
        } else {
            return "redirect:/articulos/listaArticulos";
        }
    }

    /*@GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model model) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            model.addAttribute("articulo", articulo.get());
            return "ingresarArticulo";
        } else {
            return "redirect:/articulos/listaArticulos";
        }
    }*/

    //sirve con este
    /*
    @PostMapping("/actualizar/{id}")
    public String actualizarArticulo(@PathVariable int id, @ModelAttribute Articulo articuloActualizado, @RequestParam("habitacionId") int habitacionId) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (articuloOptional.isPresent()) {
            Articulo articulo = articuloOptional.get();
            articulo.setNombre(articuloActualizado.getNombre());
            articulo.setCantidad(articuloActualizado.getCantidad());
            articulo.setDescripcion(articuloActualizado.getDescripcion());
            habitacionRepository.findById(habitacionId).ifPresent(articulo::setHabitacion);
            articuloRepository.save(articulo);
        }
        return "redirect:/articulos/listaArticulos";
    }*/

    @PostMapping("/actualizar/{id}")
    public String actualizarArticulo(@PathVariable int id, @ModelAttribute Articulo articuloActualizado, @RequestParam("habitacionId") int habitacionId, Model model) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (articuloOptional.isPresent()) {
            Optional<Habitacion> habitacionOptional = habitacionRepository.findById(habitacionId);
            if (habitacionOptional.isPresent()) {
                Articulo articulo = articuloOptional.get();
                articulo.setNombre(articuloActualizado.getNombre());
                articulo.setCantidad(articuloActualizado.getCantidad());
                articulo.setDescripcion(articuloActualizado.getDescripcion());
                articulo.setHabitacion(habitacionOptional.get());
                articuloRepository.save(articulo);
                return "redirect:/articulos/listaArticulos";
            } else {
                model.addAttribute("errorMessage", "Habitación no encontrada");
                model.addAttribute("articulo", articuloActualizado);
                return "editarArticulo";
            }
        } else {
            return "redirect:/articulos/listaArticulos";
        }
    }

    /*@PostMapping("/actualizar/{id}")
    public String actualizarArticulo(@PathVariable int id, @ModelAttribute Articulo articuloActualizado) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (articuloOptional.isPresent()) {
            Articulo articulo = articuloOptional.get();

            articulo.setNombre(articuloActualizado.getNombre());
            articulo.setCantidad(articuloActualizado.getCantidad());
            articulo.setDescripcion(articuloActualizado.getDescripcion());
            articulo.setHabitacion(articuloActualizado.getHabitacion());
            articuloRepository.save(articulo);
        }
        return "redirect:/articulos/listaArticulos";
    }*/

    /*@GetMapping("/ingresar")
    public String mostrarFormularioIngresoArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "ingresarArticulo";
    }*/
    @PostMapping("/eliminar/{id}")
    public String eliminarArticulo(@PathVariable int id) {
        articuloRepository.deleteById(id);
        return "redirect:/articulos/listaArticulos";
    }
}