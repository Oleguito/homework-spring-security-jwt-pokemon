package com.pokemonreview.api.infrastructure.repository;

import com.pokemonreview.api.domain.model.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

}
