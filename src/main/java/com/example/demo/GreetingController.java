package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Codgi - Nombre=> ,  %s!";
    private static final String template2 = "Su Codigo es: , %s!";
    private static final String template3 = "Nombre Completo: , %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "wi43155486 - Cesar Diego") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

    @GetMapping("/idat/codigo")
	public Greeting greetingCodigo(@RequestParam(value = "name", defaultValue = "wi43155486") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template2, name));
	}

    @GetMapping("/idat/nombre-completo")
	public Greeting greetingNombre(@RequestParam(value = "name", defaultValue = "Cesar Diego De La Cruz") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template3, name));
	}


}