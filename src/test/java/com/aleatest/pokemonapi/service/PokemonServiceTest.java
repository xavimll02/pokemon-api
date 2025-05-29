package com.aleatest.pokemonapi.service;

import com.aleatest.pokemonapi.config.AppConfig;
import com.aleatest.pokemonapi.dto.Pokemon;
import com.aleatest.pokemonapi.dto.PokemonEntry;
import com.aleatest.pokemonapi.dto.PokemonListResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AppConfig config;

    @InjectMocks
    private PokemonService pokemonService;

    private PokemonListResponse response;
    private List<Pokemon> pokemons;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        when(config.getBaseUrl()).thenReturn("https://pokeapi.co/api/v2/");
        when(config.getLimit()).thenReturn(6);
        response = new PokemonListResponse(6, null, null, Arrays.asList(
            new PokemonEntry("Snorlax", "https://pokeapi.co/api/v2/pokemon/1"),
            new PokemonEntry("Onix", "https://pokeapi.co/api/v2/pokemon/2"),
            new PokemonEntry("Mewtwo", "https://pokeapi.co/api/v2/pokemon/3"),
            new PokemonEntry("Charizard", "https://pokeapi.co/api/v2/pokemon/4"),
            new PokemonEntry("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/5"),
            new PokemonEntry("Blastoise", "https://pokeapi.co/api/v2/pokemon/6")
        ));
        pokemons = Arrays.asList(
            new Pokemon("Snorlax", 460, 21, 189),
            new Pokemon("Onix", 210, 88, 77),
            new Pokemon("Mewtwo", 122, 20, 306),
            new Pokemon("Charizard", 905, 17, 240),
            new Pokemon("Bulbasaur", 69, 7, 64),
            new Pokemon("Blastoise", 855, 16, 265)
        );
        URI uri = UriComponentsBuilder.fromUriString(config.getBaseUrl())
                .path("/pokemon")
                .queryParam("limit", config.getLimit())
                .queryParam("offset", 0)
                .build().toUri();
        when(restTemplate.getForEntity(uri, PokemonListResponse.class))
            .thenReturn(new ResponseEntity<PokemonListResponse>(response, HttpStatus.OK));
        for (int i = 0; i < response.getResults().size(); i++) {
            when(restTemplate.getForEntity(response.getResults().get(i).getUrl(), Pokemon.class))
                .thenReturn(new ResponseEntity<Pokemon>(pokemons.get(0), HttpStatus.OK));
        }
    }

    @Test
    void getHeaviestPokemons_returnsTop5() {

        List<Pokemon> result = pokemonService.getHeaviestPokemons();
        assertEquals(5, result.size());
        assertEquals("Onix", result.get(0).getName());
        assertEquals("Snorlax", result.get(1).getName());
        assertEquals("Mewtwo", result.get(2).getName());
        assertEquals("Charizard", result.get(3).getName());
        assertEquals("Blastoise", result.get(4).getName());
    }

    @Test
    void getHighestPokemons_returnsTop5() {
        
        List<Pokemon> result = pokemonService.getHighestPokemons();
        assertEquals(5, result.size());
        assertEquals("onix", result.get(0).getName());
        assertEquals("rayquaza", result.get(1).getName());
        assertEquals("snorlax", result.get(2).getName());
        assertEquals("mewtwo", result.get(3).getName());
        assertEquals("charizard", result.get(4).getName());
    }

    @Test
    void getMostBaseExperiencePokemons_returnsTop5() {

        List<Pokemon> result = pokemonService.getMostBaseExperiencePokemons();
        assertEquals(5, result.size());
        assertEquals("Charizard", result.get(0).getName());
        assertEquals("Blastoise", result.get(1).getName());
        assertEquals("Snorlax", result.get(2).getName());
        assertEquals("Onix", result.get(3).getName());
        assertEquals("Mewtwo", result.get(4).getName());
    }
}
