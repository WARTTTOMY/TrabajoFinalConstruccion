package com.proyecto.proyectofinal.servicio;

import com.proyecto.proyectofinal.modelo.Servicio;
import com.proyecto.proyectofinal.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements CommandLineRunner {

    @Autowired
    private ServicioRepository servicioRepository;


    @Override
    public void run(String... args) throws Exception {
        //Servicios
        Servicio servicio1 = new Servicio();
        servicio1.setId(1);
        servicio1.setNombre("Lavanderia");
        servicio1.setPrecio(10000);
        servicio1.setDetalle("Lavado de ropa");

        Servicio servicio2 = new Servicio();
        servicio2.setId(2);
        servicio2.setNombre("Guarderia");
        servicio2.setPrecio(20000);
        servicio2.setDetalle("Guarderia de mascotas");

        Servicio servicio3 = new Servicio();
        servicio3.setId(3);
        servicio3.setNombre("Peluqueria");
        servicio3.setPrecio(30000);
        servicio3.setDetalle("Corte de cabello");

        Servicio servicio4 = new Servicio();
        servicio4.setId(4);
        servicio4.setNombre("Cuidado de mascotas");
        servicio4.setPrecio(40000);
        servicio4.setDetalle("Cuidado de mascotas");


        servicioRepository.save(servicio1);
        servicioRepository.save(servicio2);
        servicioRepository.save(servicio3);
        servicioRepository.save(servicio4);


    }
}
