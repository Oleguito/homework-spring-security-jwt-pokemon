package com.pokemonreview.api.domain.model.review.exception;

import com.pokemonreview.api.presentation.exceptions.EntityNotFoundException;

public class ReviewNotFoundException extends EntityNotFoundException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
