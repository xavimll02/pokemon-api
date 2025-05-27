package com.aleatest.pokemonapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for the Pokemon API client.
 * Maps properties prefixed with "pokeapi" from application configuration
 * to configure the base URL of the API and pagination limit.
 */
@Configuration
@ConfigurationProperties(prefix = "pokeapi")
public class AppConfig {

    private int limit;
    private String baseUrl;

    /**
     * Gets the maximum number of Pokemon entries to fetch per request.
     * @return the pagination limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the maximum number of Pokemon entries to fetch per request.
     * @param limit the pagination limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets the base URL of the external Poke API.
     * @return the base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Sets the base URL of the external Poke API.
     * @param baseUrl the base URL
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}

