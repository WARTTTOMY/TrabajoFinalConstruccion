package com.proyecto.proyectofinal.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/citas")
    public String citasPage() {
        return "citas";
    }

}
