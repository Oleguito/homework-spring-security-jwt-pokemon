package com.pokemonreview.api.presentation.jwt.jwtresponse;

import lombok.Data;

@Data
public class JwtResponse {
    final String accessToken;
    final String refreshToken;
}
