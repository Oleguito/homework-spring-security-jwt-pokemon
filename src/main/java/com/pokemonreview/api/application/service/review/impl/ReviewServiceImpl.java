package com.pokemonreview.api.application.service.review.impl;

import com.pokemonreview.api.application.mappers.PokemonMapper;
import com.pokemonreview.api.application.mappers.ReviewMapper;
import com.pokemonreview.api.application.service.review.ReviewService;
import com.pokemonreview.api.domain.model.review.Review;
import com.pokemonreview.api.infrastructure.repository.PokemonRepository;
import com.pokemonreview.api.infrastructure.repository.ReviewRepository;
import com.pokemonreview.api.domain.model.review.exception.ReviewNotFoundException;
import com.pokemonreview.api.presentation.review.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;
    private final ReviewMapper reviewMapper;
    private final PokemonMapper pokemonMapper;
    
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository, ReviewMapper reviewMapper, PokemonMapper pokemonMapper) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
        this.reviewMapper = reviewMapper;
        this.pokemonMapper = pokemonMapper;
    }
    
    @Override
    public ReviewDto createReviewForPokemonWithId(int pokemonId, ReviewDto reviewDto) {
        final var review = reviewMapper.toEntity(reviewDto);
        final var pokemon = pokemonRepository.findById(pokemonId).orElseThrow();
        review.setPokemon(pokemon);
        
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(reviewDto)));
    }
    
    @Override
    public ReviewDto updateReviewWithId(int reviewId, ReviewDto reviewDto) {
        return reviewMapper.toDto(getReviewFromRepositoryById(reviewId));
    }
    
    @Override
    public void deleteReviewWithId(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    
    @Override
    public List <ReviewDto> getReviewsForPokemonId(int pokemonId) {
        final var reviews = reviewRepository.findByPokemonId(pokemonId);
        return reviews.stream()
                .map(review -> reviewMapper.toDto(review))
                .collect(Collectors.toList());
    }
    
    @Override
    public ReviewDto getById(int reviewId) {
        return reviewMapper.toDto(getReviewFromRepositoryById(reviewId));
    }
    
    private Review getReviewFromRepositoryById(int reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("can't find review, assshole!!"));
    }
    
    @Override
    public List <ReviewDto> getAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(review -> reviewMapper.toDto(review))
                .collect(Collectors.toList());
    }
}
