package com.proyecto.proyectofinal.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ingresos_pacientes")
public class IngresosPacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private String ciudad;
    private String motivo;

    @Column(name = "tiene_acompanante")
    private boolean tieneAcompañante;

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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isTieneAcompañante() {
        return tieneAcompañante;
    }

    public void setTieneAcompañante(boolean tieneAcompañante) {
        this.tieneAcompañante = tieneAcompañante;
    }
}
