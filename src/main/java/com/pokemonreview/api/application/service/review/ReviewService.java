package com.pokemonreview.api.application.service.review;

import com.pokemonreview.api.presentation.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    
    ReviewDto createReviewForPokemonWithId(int pokemonId, ReviewDto reviewDto);
    
    ReviewDto updateReviewWithId(int reviewId, ReviewDto reviewDto);
    
    void deleteReviewWithId(int reviewId);
    
    List <ReviewDto> getReviewsForPokemonId(int pokemonId);
    
    ReviewDto getById(int reviewId);
    
    List<ReviewDto> getAllReviews();
}
