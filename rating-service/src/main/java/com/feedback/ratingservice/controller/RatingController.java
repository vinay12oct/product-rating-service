package com.feedback.ratingservice.controller;

import com.feedback.ratingservice.dtos.RatingDto;
import com.feedback.ratingservice.entity.Rating;
import com.feedback.ratingservice.payload.ApiResponse;
import com.feedback.ratingservice.service.RatingService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRating(@Valid @RequestBody RatingDto ratingDto) {
        try {
            Rating createdRating = ratingService.createRating(ratingDto);
            ApiResponse response = new ApiResponse(
                "Rating created successfully.", true, HttpStatus.CREATED.value(), createdRating, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            ApiResponse response = new ApiResponse(
                ex.getMessage(), false, HttpStatus.BAD_REQUEST.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to create rating: " + ex.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getRatingsByUserId(@PathVariable int userId) {
        try {
            List<Rating> ratings = ratingService.getRatingsByUserId(userId);
            ApiResponse response = new ApiResponse(
                "Ratings fetched successfully.", true, HttpStatus.OK.value(), ratings, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Ratings not found for userId: " + userId, false, HttpStatus.NOT_FOUND.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getRatingsByProductId(@PathVariable int productId) {
        try {
            List<Rating> ratings = ratingService.getRatingsByProductId(productId);
            ApiResponse response = new ApiResponse(
                "Ratings fetched successfully.", true, HttpStatus.OK.value(), ratings, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Ratings not found for productId: " + productId, false, HttpStatus.NOT_FOUND.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/average/{productId}")
    public ResponseEntity<ApiResponse> getAverageRatingByProductId(@PathVariable int productId) {
        try {
            double averageRating = ratingService.getAverageRatingByProductId(productId);
            ApiResponse response = new ApiResponse(
                "Average rating fetched successfully.", true, HttpStatus.OK.value(), averageRating, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to fetch average rating for productId: " + productId, false, HttpStatus.NOT_FOUND.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRatings() {
        try {
            List<Rating> ratings = ratingService.getAllRatings();
            ApiResponse response = new ApiResponse(
                "All ratings fetched successfully.", true, HttpStatus.OK.value(), ratings, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Error fetching ratings.", false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<ApiResponse> deleteRating(@PathVariable int ratingId) {
        try {
            ratingService.deleteRating(ratingId);
            ApiResponse response = new ApiResponse(
                "Rating deleted successfully.", true, HttpStatus.OK.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to delete rating with id: " + ratingId, false, HttpStatus.NOT_FOUND.value(), null, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
