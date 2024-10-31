package com.proyecto.proyectofinal.repository;

import com.proyecto.proyectofinal.modelo.SalidasPacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidasPacientesRepository extends JpaRepository<SalidasPacientes, Long> {
}
