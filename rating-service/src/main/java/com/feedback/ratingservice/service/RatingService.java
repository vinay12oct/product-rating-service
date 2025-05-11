package com.feedback.ratingservice.service;

import com.feedback.ratingservice.dtos.RatingDto;
import com.feedback.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {

    // Add a new rating
    Rating createRating(RatingDto ratingDto);

    // Get all ratings by a specific user
    List<Rating> getRatingsByUserId(int userId);

    // Get all ratings for a specific product
    List<Rating> getRatingsByProductId(int productId);

    // Get average rating for a product
    double getAverageRatingByProductId(int productId);

    // Get all ratings
    List<Rating> getAllRatings();

    // Delete a rating
    void deleteRating(int ratingId);
}
