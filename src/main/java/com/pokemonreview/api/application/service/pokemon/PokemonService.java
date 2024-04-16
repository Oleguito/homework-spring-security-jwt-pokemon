package com.pokemonreview.api.application.service.pokemon;


import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import com.pokemonreview.api.presentation.pokemon.pagination.PokemonGetAllResponse;

public interface PokemonService {
    
    
    PokemonDto createPokemon(PokemonDto pokemonDto);
    
    PokemonGetAllResponse getAllPokemon(int pageNumber, int pageSize);
    
    PokemonDto getPokemonById(int id);
    
    PokemonDto updatePokemonById(int id, PokemonDto replacer);
    
    
    void deletePokemonById(int id);
}
