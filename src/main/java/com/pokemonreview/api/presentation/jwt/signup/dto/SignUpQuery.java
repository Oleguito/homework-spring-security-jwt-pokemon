package com.pokemonreview.api.presentation.jwt.signup.dto;

public record SignUpQuery(
        String login,
        String password
) {
}
