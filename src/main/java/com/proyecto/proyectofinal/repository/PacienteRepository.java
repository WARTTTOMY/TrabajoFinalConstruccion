package com.proyecto.proyectofinal.repository;

import com.proyecto.proyectofinal.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
