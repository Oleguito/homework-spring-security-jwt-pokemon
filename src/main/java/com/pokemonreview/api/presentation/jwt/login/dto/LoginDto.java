package com.pokemonreview.api.presentation.jwt.login.dto;

import lombok.Data;

@Data
public class LoginDto {
    private final String login;
    private final String password;
}
