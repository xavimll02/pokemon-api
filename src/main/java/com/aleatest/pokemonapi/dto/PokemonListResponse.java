package com.aleatest.pokemonapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Represents the paginated response returned by the Poke API
 * when listing Pokemon entries.
 */
@AllArgsConstructor
public class PokemonListResponse {
    
    private int count;
    private String next;
    private String previous;
    private List<PokemonEntry> results;

    /**
     * Gets the total number of available Pokemon entries.
     * @return the total count of Pokemon entries
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the URL to the next page of results, if available.
     * @return the next page URL or null if none
     */
    public String getNext() {
        return next;
    }

    /**
     * Gets the URL to the previous page of results, if available.
     * @return the previous page URL or null if none
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * Gets the list of Pokemon entries in the current page.
     * @return the list of Pokemon entries
     */
    public List<PokemonEntry> getResults() {
        return results;
    }
}
