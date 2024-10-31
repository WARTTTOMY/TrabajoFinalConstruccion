package com.proyecto.proyectofinal.modelo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="pacientes")
public class Paciente {

    @Id
    private int id;

    @Column(name = "nom_paciente")
    private String nomPaciente;

    @Column(name = "ape_paciente")
    private String apePaciente;

    @Column(name = "edad_paciente")
    private int edad_paciente;

    @Column(name = "direccion_paciente")
    private String direccion_paciente;

    @Column(name = "telefono_paciente")
    private String telefono_paciente;

    @Column(name = "eps_paciente")
    private String eps_paciente;
    
    @ManyToMany
    @JoinTable(
            name = "paciente_servicio",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> servicios;

    public Paciente() {
    }

    public Paciente(int id, String nomPaciente, String apePaciente, int edad_paciente, String direccion_paciente, String telefono_paciente, String eps_paciente) {
        this.id = id;
        this.nomPaciente = nomPaciente;
        this.apePaciente = apePaciente;
        this.edad_paciente = edad_paciente;
        this.direccion_paciente = direccion_paciente;
        this.telefono_paciente = telefono_paciente;
        this.eps_paciente = eps_paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPaciente() {
        return nomPaciente;
    }

    public void setNomPaciente(String nomPaciente) {
        this.nomPaciente = nomPaciente;
    }

    public String getApePaciente() {
        return apePaciente;
    }

    public void setApePaciente(String apePaciente) {
        this.apePaciente = apePaciente;
    }

    public int getEdad_paciente() {
        return edad_paciente;
    }

    public void setEdad_paciente(int edad_paciente) {
        this.edad_paciente = edad_paciente;
    }

    public String getDireccion_paciente() {
        return direccion_paciente;
    }

    public void setDireccion_paciente(String direccion_paciente) {
        this.direccion_paciente = direccion_paciente;
    }

    public String getTelefono_paciente() {
        return telefono_paciente;
    }

    public void setTelefono_paciente(String telefono_paciente) {
        this.telefono_paciente = telefono_paciente;
    }

    public String getEps_paciente() {
        return eps_paciente;
    }

    public void setEps_paciente(String eps_paciente) {
        this.eps_paciente = eps_paciente;
    }


    public void setServicios(List<Servicio> servicios) {
    }
}
