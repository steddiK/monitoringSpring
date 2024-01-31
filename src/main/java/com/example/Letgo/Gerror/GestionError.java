package com.example.Letgo.Gerror;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Gestionerror")
public class GestionError {
    @GetMapping
    public String handleError() {
        // Logic to handle errors and return the error view
        return "error";
    }
}
