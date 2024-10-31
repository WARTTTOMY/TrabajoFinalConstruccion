package com.proyecto.proyectofinal.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime fecha;
    private String hora;


    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado funcionario;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Empleado getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Empleado funcionario) {
        this.funcionario = funcionario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
