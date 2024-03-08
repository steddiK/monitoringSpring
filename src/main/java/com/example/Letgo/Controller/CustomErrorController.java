package com.example.Letgo.Controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;

@RestController
public class CustomErrorController {
    Logger logger= LoggerFactory.getLogger(CustomErrorController.class);
    private final Counter Erreur400;
    private final Counter Erreur401;
    private final Counter Erreur403;
    private final Counter Erreur404;
    private final Counter Erreur500;

    public CustomErrorController(PrometheusMeterRegistry registry) {
        this.Erreur400 = Counter.builder("Erreur400")
                .description("Erreur_400")
                .register(registry);
        this.Erreur401 = Counter.builder("Erreur401")
                .description("Erreur_401")
                .register(registry);
        this.Erreur403 = Counter.builder("Erreur403")
                .description("Erreur_403")
                .register(registry);
        this.Erreur404 = Counter.builder("Erreur404")
                .description("Erreur_404")
                .register(registry);
        this.Erreur500 = Counter.builder("Erreur500")
                .description("Erreur_500")
                .register(registry);
    }


    public ResponseEntity<String> generateError400() {
        Erreur400.increment();
        throw new RuntimeException(String.valueOf(HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<String> generateError401() {
        Erreur401.increment();
        throw new RuntimeException(String.valueOf(HttpStatus.UNAUTHORIZED));
    }

    public ResponseEntity<String> generateError403() {
        Erreur403.increment();
        throw new RuntimeException(String.valueOf(HttpStatus.FORBIDDEN));

    }

    public ResponseEntity<String> generateError404() {
        Erreur404.increment();
        throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<String> generateError500() {
        Erreur500.increment();
        throw new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    
    @GetMapping("/")
    public String accueil() {
    	return "welcome";
    }
    
    @GetMapping("/runError")
    public void randomeo()  {
        Random random= new Random();
        int randomNbr=0;
        for(int i=1;i<10;i++){
            randomNbr=random.nextInt(20)+1;
            try{
                switch(randomNbr){
                    case 2:
                        generateError400();
                        logger.error("Erreur400");
                        break;
                    case 3:
                        generateError401();
                        break;
                    case 4:
                        generateError403();
                        break;
                    case 5:
                        generateError404();
                        break;
                    case 6:
                        generateError500();
                        break;
                    default:
                        break;

                }
            }
            catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }
    
    public static List<String> readFromFile() {
        try {
            Path path = Paths.get("D:\\Utilisateurs\\steddi.andritiana\\Desktop\\Letgo\\logging\\myapplication.log");
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/runMonitoring")
    public void runfile() throws IOException {
        List<String> lines = readFromFile();
        if (lines != null) {
            for (String line : lines) {
                if (line.startsWith("j")) {
                    System.out.println(line);
                    String v= line.substring(29,31);
                    if(v.equals("400")){
                        Erreur400.increment();
                    }
                    if(v.equals("401")){
                        Erreur401.increment();
                    }
                    if(v.equals("403")){
                        Erreur403.increment();
                    }
                    if(v.equals("404")){
                        Erreur404.increment();
                    }
                    if(v.equals("500")) {
                        Erreur500.increment();
                    }
                }
            }
        } else {
            System.out.println("Erreur lors de la lecture du fichier");
        }
        Path path = Paths.get("D:\\Utilisateurs\\steddi.andritiana\\Desktop\\Letgo\\logging\\myapplication.log");
        Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
