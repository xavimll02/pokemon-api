package com.aleatest.pokemonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import com.aleatest.pokemonapi.dto.ErrorResponse;

/**
 * Global exception handler for handling errors from external HTTP calls.
 * Intercepts and transforms various RestTemplate exceptions into
 * consistent API error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles HTTP 4xx client errors returned by external services.
     * @param ex the {@link HttpClientErrorException} thrown
     * @return {@link ResponseEntity} with error details and status code
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientError(HttpClientErrorException ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Client error", ex.getResponseBodyAsString()),
            ex.getStatusCode()
        );
    }

    /**
     * Handles HTTP 5xx server errors returned by external services.
     * @param ex the {@link HttpServerErrorException} thrown
     * @return {@link ResponseEntity} with error details and status code
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpServerError(HttpServerErrorException ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Server error", ex.getResponseBodyAsString()),
            ex.getStatusCode()
        );
    }

    /**
     * Handles connectivity issues with the external service.
     * @param ex the {@link ResourceAccessException} thrown
     * @return {@link ResponseEntity} with error message and 503 status
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccess(ResourceAccessException ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Service unreachable", ex.getMessage()),
            HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    /**
     * Handles any other {@link RestClientException} not specifically caught.
     * @param ex the {@link RestClientException} thrown
     * @return {@link ResponseEntity} with error message and 502 status
     */
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleGeneral(RestClientException ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Unexpected error", ex.getMessage()),
            HttpStatus.BAD_GATEWAY
        );
    }
}
