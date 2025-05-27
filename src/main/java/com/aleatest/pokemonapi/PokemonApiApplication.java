package com.aleatest.pokemonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Pokemon API application.
 * This class bootstraps the Spring Boot application.
 */
@SpringBootApplication
public class PokemonApiApplication {

	/**
	 * Starts the Pokemon API application.
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PokemonApiApplication.class, args);
	}

}
