package com.aleatest.pokemonapi.dto;

/**
 * Represents a Pokemon entry from a paginated list response.
 * Contains the Pokemon's name and a URL to its detailed resource.
 */
public class PokemonEntry {
    
    private String name;
    private String url;

    /**
     * Gets the name of the Pokemon.
     * @return the Pokemon's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the URL pointing to the detailed Pokemon data.
     * @return the URL of the Pokemon resource
     */
    public String getUrl() {
        return url;
    }
}
