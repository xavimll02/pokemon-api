package com.aleatest.pokemonapi.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.aleatest.pokemonapi.config.AppConfig;
import com.aleatest.pokemonapi.dto.Pokemon;
import com.aleatest.pokemonapi.dto.PokemonEntry;
import com.aleatest.pokemonapi.dto.PokemonListResponse;

/**
 * Service class for fetching the top 5 Pokemons with more weight, height and base experience.
 */
@Service
public class PokemonService {
    
    private final AppConfig config;
    private final RestTemplate restTemplate;

    /**
     * Constructor for PokemonService.
     * @param config the application configuration
     * @param restTemplate the {@link RestTemplate} used to perform HTTP requests
     */
    public PokemonService(AppConfig config, RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves a list of all Pokemon entries from the Poke API.
     * @return a list of Pokemon entries
     */
    private List<PokemonEntry> getPokemonEntries() {
        
        List<PokemonEntry> pokemonEntries = new ArrayList<>();
        int offset = 0;
        int limit = config.getLimit();
        String next = null;
        do {
            URI uri = UriComponentsBuilder.fromUriString(config.getBaseUrl())
                .path("/pokemon")
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build().toUri();
            ResponseEntity<PokemonListResponse> response = restTemplate
                .getForEntity(uri, PokemonListResponse.class);
            PokemonListResponse body = response.getBody();
            if (body != null) {
                pokemonEntries.addAll(body.getResults());
                next = body.getNext();
            }
            offset += limit;
        } while (next != null);
        return pokemonEntries;
    }

    /**
     * Retrieves Pokemon information from its URL.
     * @param url the URL to fetch the Pokemon details from
     * @return a Pokemon
     */
    private Pokemon getPokemon(String url) {
        
        ResponseEntity<Pokemon> response = restTemplate
            .getForEntity(url, Pokemon.class);
        return response.getBody();
    }

    /**
     * Retrieves the top 5 Pokemon based on a given feature.
     * @param comparator the comparator defining the ranking criteria
     * @return a list of top 5 Pokemon sorted by the comparator in descending order
     */
    private List<Pokemon> getTopPokemonsByFeature(Comparator<Pokemon> comparator) {

        PriorityQueue<Pokemon> top5 = new PriorityQueue<>(5, comparator);
        List<PokemonEntry> pokemonEntries = getPokemonEntries();

        for (PokemonEntry entry : pokemonEntries) {
            Pokemon pokemon = getPokemon(entry.getUrl());
            if (pokemon == null) continue;
            if (top5.size() < 5) {
                top5.offer(pokemon);
            } else if (comparator.compare(pokemon, top5.peek()) > 0) {
                top5.poll();
                top5.offer(pokemon);
            }
        }

        return top5.stream()
            .sorted(comparator.reversed())
            .collect(Collectors.toList());
    }

    /**
     * Retrieves the top 5 heaviest Pokemons.
     * @return a list of the top 5 heaviest Pokemons
     */
    @Cacheable(value = "pokemonsCache", key = "'heaviest'")
    public List<Pokemon> getHeaviestPokemons() {
        return this.getTopPokemonsByFeature(Comparator.comparingInt(Pokemon::getWeight));
    }

    /**
     * Retrieves the top 5 highest Pokemons.
     * @return a list of the top 5 highest Pokemons
     */
    @Cacheable(value = "pokemonsCache", key = "'highest'")
    public List<Pokemon> getHighestPokemons() { 
        return this.getTopPokemonsByFeature(Comparator.comparingInt(Pokemon::getHeight));
    }

    /**
     * Retrieves the 5 Pokemons with more base experience.
     * @return a list of the 5 Pokemons with more base experience
     */
    @Cacheable(value = "pokemonsCache", key = "'mostBaseExperience'")
    public List<Pokemon> getMostBaseExperiencePokemons() {
         return this.getTopPokemonsByFeature(Comparator.comparingInt(Pokemon::getBaseExperience));
    }
}
