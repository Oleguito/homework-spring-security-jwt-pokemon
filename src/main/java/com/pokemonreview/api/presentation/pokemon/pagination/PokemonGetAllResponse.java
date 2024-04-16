package com.pokemonreview.api.presentation.pokemon.pagination;

import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import lombok.Data;

import java.util.List;

@Data
public class PokemonGetAllResponse {
    private List <PokemonDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}
