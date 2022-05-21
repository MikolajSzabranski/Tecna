package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PersonRepository personRepository){
		return args -> {
			Person newPerson = new Person(
					"Antek",
					"Kowalski",
					"AntonKov@gmail.com"
			);
			personRepository.save(newPerson);
		};
	}
}
