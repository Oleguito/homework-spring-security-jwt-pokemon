package com.pokemonreview.api.presentation.review.dto;

import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private int id;
    private String title;
    private String content;
    private int stars;
    private PokemonDto pokemon;
}
