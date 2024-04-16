package com.pokemonreview.api.application.service.pokemon.impl;

import com.pokemonreview.api.application.mappers.PokemonMapper;
import com.pokemonreview.api.presentation.pokemon.dto.PokemonDto;
import com.pokemonreview.api.presentation.pokemon.pagination.PokemonGetAllResponse;
import com.pokemonreview.api.presentation.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.domain.model.Pokemon;
import com.pokemonreview.api.infrastructure.repository.PokemonRepository;
import com.pokemonreview.api.application.service.pokemon.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    
    private PokemonRepository pokemonRepository;
    private PokemonMapper pokemonMapper;
    
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository, PokemonMapper pokemonMapper) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonMapper = pokemonMapper;
    }
    
    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        
        return pokemonMapper.toDto(
                pokemonRepository.save(pokemonMapper.toEntity(pokemonDto))
        );
    }
    
    @Override
    public PokemonGetAllResponse getAllPokemon(int pageNumber, int pageSize) {
        
        final var pageable = PageRequest.of(pageNumber, pageSize);
        Page <Pokemon> pokemons = pokemonRepository.findAll(pageable);
        final var listOfPokemons = pokemons.getContent();
        final var content = listOfPokemons.stream()
                .map(pokemon -> pokemonMapper.toDto(pokemon))
                .collect(Collectors.toList());
        final var returnResult = new PokemonGetAllResponse();
        returnResult.setContent(content);
        returnResult.setPageNumber(pokemons.getNumber());
        returnResult.setPageSize(pokemons.getSize());
        returnResult.setTotalElements(pokemons.getTotalElements());
        returnResult.setLast(pokemons.isLast());
        return returnResult;
    }
    
    @Override
    public PokemonDto getPokemonById(int id) {
        final var pokemon = pokemonRepository.findById(id).orElseThrow(
                () -> {
                    return new PokemonNotFoundException("Pokemon not found, asshole!");
                }
        );
        return pokemonMapper.toDto(pokemon);
    }
    
    @Override
    public PokemonDto updatePokemonById(int id, PokemonDto replacer) {
        final var found = pokemonRepository.findById(id).orElseThrow(
                () -> new PokemonNotFoundException("Pokemom not founnd!!!")
        );
        found.setName(replacer.getName());
        found.setType(replacer.getType());
        return pokemonMapper.toDto(pokemonRepository.save(found));
    }
    
    @Override
    public void deletePokemonById(int id) {
        pokemonRepository.deleteById(id);
    }
    
    
}
