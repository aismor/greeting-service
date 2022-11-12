package br.com.aismor.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aismor.configuration.GreetingConfiguration;
import br.com.aismor.model.Greeting;

@RestController
public class GreetingController {

	private static final String TEMPLATE = "%s, %s!";
	private final AtomicLong COUNTER = new AtomicLong();

	@Autowired
	private GreetingConfiguration configuration;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {
		if (name.isEmpty())
			name = configuration.getDefaultValue();
		return new Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, configuration.getGreeting(), name));
	}

}
