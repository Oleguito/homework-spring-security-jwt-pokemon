package com.pokemonreview.api.infrastructure.repository;

import com.pokemonreview.api.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
    List <Review> findByPokemonId(int pokemonId);

}
