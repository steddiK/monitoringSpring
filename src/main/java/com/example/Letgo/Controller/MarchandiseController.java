package com.example.Letgo.Controller;

import com.example.Letgo.Entity.Marchandises;
import com.example.Letgo.Service.MarchandiseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MarchandiseController {

    @Autowired
    private final MarchandiseServices marchandiseServices;

    public MarchandiseController(MarchandiseServices marchandiseServices) {
        this.marchandiseServices = marchandiseServices;
    }

    @GetMapping("/march")
    public String welcome(){
        return "Welcome to Marchandise";
    }

    @PostMapping("/saveoMarch")
    public String creer(@RequestBody Marchandises m){
        this.marchandiseServices.creer(m);
        return "Marchandise bien creer";
    }
    @GetMapping(value = "/{id}")
    public void affichOneMarch(@PathVariable int id){
        this.marchandiseServices.rechercher(id);
    }
    @GetMapping(value = "/listMarch")
    public void affichAllMarch(){
        this.marchandiseServices.rechercherAll();
    }


}
