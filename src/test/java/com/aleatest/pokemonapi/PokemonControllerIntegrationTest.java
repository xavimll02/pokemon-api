package com.aleatest.pokemonapi;

import com.aleatest.pokemonapi.dto.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetHeaviestPokemons() {
        
        ResponseEntity<List<Pokemon>> response = restTemplate.exchange(
            "/api/v1/heaviest",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Pokemon>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Pokemon> pokemons = response.getBody();

        assertNotNull(pokemons);
        assertEquals(5, pokemons.size());

        for (int i = 0; i < pokemons.size() - 1; i++) {
            assertTrue(pokemons.get(i).getWeight() >= pokemons.get(i + 1).getWeight());
        }
    }

    @Test
    void testGetHighestPokemons() {
        ResponseEntity<List<Pokemon>> response = restTemplate.exchange(
            "/api/v1/highest",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Pokemon>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Pokemon> pokemons = response.getBody();

        assertNotNull(pokemons);
        assertEquals(5, pokemons.size());

        for (int i = 0; i < pokemons.size() - 1; i++) {
            assertTrue(pokemons.get(i).getHeight() >= pokemons.get(i + 1).getHeight());
        }
    }

    @Test
    void testGetMostBaseExperiencePokemons() {
        ResponseEntity<List<Pokemon>> response = restTemplate.exchange(
            "/api/v1/most-experience",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Pokemon>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Pokemon> pokemons = response.getBody();

        assertNotNull(pokemons);
        assertEquals(5, pokemons.size());

        for (int i = 0; i < pokemons.size() - 1; i++) {
            assertTrue(pokemons.get(i).getBaseExperience() >= pokemons.get(i + 1).getBaseExperience());
        }
    }
}