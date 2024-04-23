package com.pokemonreview.api.domain.model.pokemon.exception;

import com.pokemonreview.api.presentation.exceptions.EntityNotFoundException;

public class PokemonNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1;
    
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
