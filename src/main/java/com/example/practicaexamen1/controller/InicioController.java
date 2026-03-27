package com.example.practicaexamen1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioController {
    @GetMapping({"/", "/presentation/inicio"})
    public String inicio(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        // Si hubo error de login, pasamos un mensaje a la vista
        if (error != null) {
            model.addAttribute("errorLogin", "Usuario o contraseña incorrectos.");
        }

        // Retorna el nombre del template: src/main/resources/templates/inicio.html
        return "inicio";
    }
}
