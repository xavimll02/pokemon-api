package com.aleatest.pokemonapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleatest.pokemonapi.dto.Pokemon;
import com.aleatest.pokemonapi.service.PokemonService;

/**
 * REST controller that exposes endpoints to retrieve the top 5 Pokemons
 * based on weight, height, or base experience.
 */
@RestController
@RequestMapping("/api/v1")
public class PokemonController {
    
    private final PokemonService pokemonService;
    
    /** 
     * Constructs a new PokemonController with the given PokemonService.
     * @param pokemonService the service used to retrieve Pokemon data
     */
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Retrieves the top 5 heaviest Pokemons.
     * @return {@link ResponseEntity} containing a list of the 5 heaviest Pokemons
     */
    @GetMapping("/heaviest")
    public ResponseEntity<List<Pokemon>> getHeaviestPokemons() {
        List<Pokemon> pokemons = this.pokemonService.getHeaviestPokemons();
        return ResponseEntity.ok(pokemons);
    }

    /**
     * Retrieves the top 5 highest Pokemons.
     * @return {@link ResponseEntity} containing a list of the 5 highest Pokemons
     */
    @GetMapping("/highest")
    public ResponseEntity<List<Pokemon>> getHighestPokemons() {
        List<Pokemon> pokemons = this.pokemonService.getHighestPokemons();
        return ResponseEntity.ok(pokemons);
    }

    /**
     * Retrieves the 5 Pokemons with more base experience.
     * @return {@link ResponseEntity} containing a list of the 5 Pokemons with more base experience
     */
    @GetMapping("/most-experience")
    public ResponseEntity<List<Pokemon>> getMostExperiencePokemons() {
        List<Pokemon> pokemons = this.pokemonService.getMostExperiencePokemons();
        return ResponseEntity.ok(pokemons);
    }
}
