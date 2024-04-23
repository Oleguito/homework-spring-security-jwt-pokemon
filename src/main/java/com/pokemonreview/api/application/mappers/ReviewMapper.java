package com.pokemonreview.api.application.mappers;

import com.pokemonreview.api.domain.model.review.Review;
import com.pokemonreview.api.presentation.review.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    
    private final PokemonMapper pokemonMapper;
    
    public ReviewMapper(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }
    
    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        if (reviewDto.getPokemon() != null) {
            review.setPokemon(pokemonMapper.toEntity(reviewDto.getPokemon()));
        }
        return review;
    }
    
    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        if (review.getPokemon() != null) {
            reviewDto.setPokemon(pokemonMapper.toDto(review.getPokemon()));
        }
        return reviewDto;
    }
}
