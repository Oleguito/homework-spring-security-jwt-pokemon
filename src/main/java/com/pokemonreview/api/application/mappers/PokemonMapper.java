package com.pokemonreview.api.application.mappers;

import com.pokemonreview.api.domain.model.Pokemon;
import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {
    public PokemonDto toDto(Pokemon pokemon) {
        return PokemonDto.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .build();
    }
    
    public Pokemon toEntity(PokemonDto pokemonDto) {
        return Pokemon.builder()
                .id(pokemonDto.getId())
                .name(pokemonDto.getName())
                .type(pokemonDto.getType())
                .build();
    }
}
