package com.proyecto.proyectofinal.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }


    public String error() {
        return "/error";
    }

}
