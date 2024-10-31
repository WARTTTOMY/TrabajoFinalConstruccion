package com.proyecto.proyectofinal.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="salidas_pacientes")
public class SalidasPacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDate fechaSalida;
    private double montoTotal;

    @ManyToMany
    @JoinTable(
            name = "salida_servicio",
            joinColumns = @JoinColumn(name = "salida_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> serviciosUtilizados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Servicio> getServiciosUtilizados() {
        return serviciosUtilizados;
    }

    public void setServiciosUtilizados(List<Servicio> serviciosUtilizados) {
        this.serviciosUtilizados = serviciosUtilizados;
    }
}