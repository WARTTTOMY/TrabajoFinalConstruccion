package com.proyecto.proyectofinal.repository;

import com.proyecto.proyectofinal.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    List<Servicio> findAllByPacientes_Id(Long pacienteIdInt);
}
