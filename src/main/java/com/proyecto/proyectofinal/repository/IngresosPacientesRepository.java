package com.proyecto.proyectofinal.repository;

import com.proyecto.proyectofinal.modelo.IngresosPacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresosPacientesRepository extends JpaRepository<IngresosPacientes, Long> {
}
