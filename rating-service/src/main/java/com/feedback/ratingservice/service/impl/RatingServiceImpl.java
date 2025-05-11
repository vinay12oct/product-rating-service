package com.feedback.ratingservice.service.impl;

import com.feedback.ratingservice.dtos.RatingDto;
import com.feedback.ratingservice.entity.Rating;
import com.feedback.ratingservice.exception.ResourceNotFoundException;
import com.feedback.ratingservice.repository.RatingRepository;
import com.feedback.ratingservice.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(RatingDto ratingDto) {
        try {
            if (ratingDto.getRating() < 1 || ratingDto.getRating() > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }

            Rating rating = new Rating();
            rating.setUserId(ratingDto.getUserId());
            rating.setProductId(ratingDto.getProductId());
            rating.setRating(ratingDto.getRating());
            rating.setFeedbackText(ratingDto.getFeedbackText());
            rating.setImageUrl(ratingDto.getImageUrl());

            return ratingRepository.save(rating);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create rating: " + ex.getMessage());
        }
    }

    @Override
    public List<Rating> getRatingsByUserId(int userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        if (ratings.isEmpty()) {
            throw new ResourceNotFoundException("Rating", "userId", userId);
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatingsByProductId(int productId) {
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        if (ratings.isEmpty()) {
            throw new ResourceNotFoundException("Rating", "productId", productId);
        }
        return ratings;
    }

    @Override
    public double getAverageRatingByProductId(int productId) {
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        if (ratings.isEmpty()) {
            throw new ResourceNotFoundException("Rating", "productId", productId);
        }

        return ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public void deleteRating(int ratingId) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", "id", ratingId));
        ratingRepository.delete(rating);
    }

	
}
