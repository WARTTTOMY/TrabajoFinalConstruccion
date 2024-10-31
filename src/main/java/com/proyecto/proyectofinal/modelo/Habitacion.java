package com.proyecto.proyectofinal.modelo;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    private int id;

//    @Column(name = "numero_habitacion")
//    private String num_habitacion;

    @Column (name = "tipo_habitacion")
    private String tipo_habitacion;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Articulo> articulos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getNum_habitacion() {
//        return num_habitacion;
//    }
//
//    public void setNum_habitacion(String num_habitacion) {
//        this.num_habitacion = num_habitacion;
//    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}

