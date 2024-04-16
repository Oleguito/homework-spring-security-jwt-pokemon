package com.pokemonreview.api.presentation.exceptions;

public class ReviewNotFoundException extends  RuntimeException{
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
