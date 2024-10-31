package com.proyecto.proyectofinal.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    //COMENTARIO
    @Id
    private Long id;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "apellido_empleado")
    private String apellidoEmpleado;

    @Column(name = "edad_empleado")
    private int edad_empleado;

    @Column(name = "telefono_empleado")
    private String telefono_empleado;

    @Column(name = "fecha_nacimiento_empleado")
    private String fecha_nacimiento_empleado;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public int getEdad_empleado() {
        return edad_empleado;
    }

    public void setEdad_empleado(int edad_empleado) {
        this.edad_empleado = edad_empleado;
    }

    public String getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getFecha_nacimiento_empleado() {
        return fecha_nacimiento_empleado;
    }

    public void setFecha_nacimiento_empleado(String fecha_nacimiento_empleado) {
        this.fecha_nacimiento_empleado = fecha_nacimiento_empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
