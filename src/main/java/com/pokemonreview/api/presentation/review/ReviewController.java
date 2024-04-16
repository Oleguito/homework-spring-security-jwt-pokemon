package com.pokemonreview.api.presentation.review;

import com.pokemonreview.api.application.service.review.ReviewService;
import com.pokemonreview.api.presentation.review.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    // Create
    @PostMapping("/create/for-pokemon/{id}")
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable("id") int pokemonId,
            @RequestBody ReviewDto reviewDto
    ) {
        ReviewDto createdReview = reviewService.createReviewForPokemonWithId(pokemonId, reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
    
    // Read all reviews
    @GetMapping("/all")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> allReviews = reviewService.getAllReviews();
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }
    
    // Read review by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable int id) {
        ReviewDto review = reviewService.getById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update review
    @PutMapping("/update/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReviewWithId(id, reviewDto);
        if (updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete review
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        reviewService.deleteReviewWithId(id);
        return ResponseEntity.ok("Review successfully deleted!");
    }
    
    // Get reviews by Pokemon ID
    @GetMapping("/for-pokemon/{pokemonId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable int pokemonId) {
        List<ReviewDto> reviewsForPokemon = reviewService.getReviewsForPokemonId(pokemonId);
        return new ResponseEntity<>(reviewsForPokemon, HttpStatus.OK);
    }
}
