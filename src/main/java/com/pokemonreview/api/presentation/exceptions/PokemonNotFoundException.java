package com.pokemonreview.api.presentation.exceptions;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;
    
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
