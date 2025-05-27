package com.aleatest.pokemonapi.dto;

/**
 * Represents a structured error response returned by the API.
 * Contains a short error identifier and a detailed message.
 */
public class ErrorResponse {
    
    private String error;
    private String message;

    /**
     * Constructs an ErrorResponse with the specified error and message.
     * @param error a short identifier for the error type
     * @param message a detailed description of the error
     */
    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    /**
     * Returns the short identifier for the error type.
     * @return the error identifier
     */
    public String getError() {
        return error;
    }

    /**
     * Returns the detailed description of the error.
     * @return the error message
     */
    public String getMessage() {
        return message;
    }
}
