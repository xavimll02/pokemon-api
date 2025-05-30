/*
package com.aleatest.pokemonapi.exception;

import com.aleatest.pokemonapi.controller.PokemonController;
import com.aleatest.pokemonapi.service.PokemonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokemonController.class)
public class PokemonExceptionHandlingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PokemonService pokemonService;

    @Test
    public void whenHttpClientError_thenHandledAs4xx() throws Exception {
        
        when(pokemonService.getHeaviestPokemons())
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Pokemon not found"));

        mockMvc.perform(get("/api/v1/heaviest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Client error"))
            .andExpect(jsonPath("$.message").value("404 Pokemon not found"));
    }

    @Test
    public void whenHttpServerError_thenHandledAs5xx() throws Exception {
        
        when(pokemonService.getHeaviestPokemons())
            .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred"));

        mockMvc.perform(get("/api/v1/heaviest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Server error"))
            .andExpect(jsonPath("$.message").value("500 Internal server error occurred"));
    }

    @Test
    public void whenResourceAccessException_thenHandledAsServiceUnavailable() throws Exception {
        
        when(pokemonService.getHeaviestPokemons())
            .thenThrow(new ResourceAccessException("Failed to access external resource or service"));

        mockMvc.perform(get("/api/v1/heaviest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isServiceUnavailable())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Service unreachable"))
            .andExpect(jsonPath("$.message").value("Failed to access external resource or service"));
    }

    @Test
    public void whenRestClientException_thenHandledAsInternalError() throws Exception {
        
        when(pokemonService.getHeaviestPokemons())
            .thenThrow(new RestClientException("Error during response deserialization"));

        mockMvc.perform(get("/api/v1/heaviest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadGateway())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.error").value("Unexpected error"))
            .andExpect(jsonPath("$.message").value("Error during response deserialization"));
    }
}
*/