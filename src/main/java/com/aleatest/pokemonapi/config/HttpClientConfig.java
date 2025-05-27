package com.aleatest.pokemonapi.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class that defines the HTTP client used to call the Poke API.
 * Provides a pre-configured {@link RestTemplate} bean with default headers set to accept JSON responses.
 */
@Configuration
public class HttpClientConfig {

    /**
     * Creates a {@link RestTemplate} bean with default "Accept: application/json" header.
     * @param builder the {@link RestTemplateBuilder} to customize the client
     * @return a configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
