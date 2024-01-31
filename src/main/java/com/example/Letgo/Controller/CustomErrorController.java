package com.example.Letgo.Controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class CustomErrorController {

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

    @GetMapping("/Error400")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void generateError400() {
        Erreur400.increment();
    }

    @GetMapping("/Error401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void generateError401() {
        Erreur401.increment();
    }

    @GetMapping("/Error403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void generateError403() {
        Erreur403.increment();
    }

    @GetMapping("/Error404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void generateError404() {
        Erreur404.increment();
    }

    @GetMapping("/Error500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void generateError500() {
        Erreur500.increment();
    }

}
