package com.proyecto.proyectofinal.controlador;

import com.proyecto.proyectofinal.modelo.Habitacion;
import com.proyecto.proyectofinal.repository.HabitacionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionControlador {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping
    public List<Habitacion> listarHabitaciones() {

        return habitacionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Habitacion> crearHabitacion(@Valid @RequestBody Habitacion habitacion) {
        Habitacion nuevaHabitacion = habitacionRepository.save(habitacion);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacionPorId(@PathVariable int id) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(id);
        return habitacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable int id, @Valid @RequestBody Habitacion detallesHabitacion) {
        Optional<Habitacion> habitacionOptional = habitacionRepository.findById(id);
        if (!habitacionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Habitacion habitacion = habitacionOptional.get();
        habitacion.setId(detallesHabitacion.getId());
//        habitacion.setNum_habitacion(detallesHabitacion.getNum_habitacion());
        habitacion.setTipo_habitacion(detallesHabitacion.getTipo_habitacion());

        Habitacion habitacionActualizada = habitacionRepository.save(habitacion);
        return ResponseEntity.ok(habitacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable int id) {
        if (!habitacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        habitacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
