package com.aleatest.pokemonapi.controller;
import com.aleatest.pokemonapi.dto.Pokemon;
import com.aleatest.pokemonapi.service.PokemonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PokemonService pokemonService;

    private List<Pokemon> pokemons;

    @BeforeEach
    public void setUp() {
        pokemons = Arrays.asList(
            new Pokemon("Snorlax", 50, 21, 460)
        );
    }

    @Test
    public void whenGetHeaviest_thenReturnPokemonList() throws Exception {

        when(pokemonService.getHeaviestPokemons()).thenReturn(pokemons);

        mockMvc.perform(get("/api/v1/heaviest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Snorlax"))
            .andExpect(jsonPath("$[0].weight").value(460))
            .andExpect(jsonPath("$[0].height").value(21))
            .andExpect(jsonPath("$[0].base_experience").value(50));
    }

    @Test
    public void whenGetHighest_thenReturnPokemonList() throws Exception {
        
        when(pokemonService.getHighestPokemons()).thenReturn(pokemons);

        mockMvc.perform(get("/api/v1/highest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Snorlax"))
            .andExpect(jsonPath("$[0].weight").value(460))
            .andExpect(jsonPath("$[0].height").value(21))
            .andExpect(jsonPath("$[0].base_experience").value(50));
    }

    @Test
    public void whenGetMostExperienced_thenReturnPokemonList() throws Exception {
        
        when(pokemonService.getMostBaseExperiencePokemons()).thenReturn(pokemons);

        mockMvc.perform(get("/api/v1/most-experience")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Snorlax"))
            .andExpect(jsonPath("$[0].weight").value(460))
            .andExpect(jsonPath("$[0].height").value(21))
            .andExpect(jsonPath("$[0].base_experience").value(50));
    }
}