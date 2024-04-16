package com.pokemonreview.api.presentation.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {
    private int id;
    private String name;
    private String type;
}
