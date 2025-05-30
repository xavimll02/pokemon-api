package com.aleatest.pokemonapi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a simplified Pokemon with basic attributes.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    private String name;
    private int baseExperience;
    private int height;
    private int weight;

    /**
     * Gets the name of the Pokemon.
     * @return the Pokemon's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base experience gained when the Pokemon is defeated.
     * @return the base experience value
     */
    public int getBaseExperience() {
        return baseExperience;
    }

    /**
     * Gets the height of the Pokemon.
     * @return the Pokemon's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the weight of the Pokemon.
     * @return the Pokemon's weight
     */
    public int getWeight() {
        return weight;
    }
}
