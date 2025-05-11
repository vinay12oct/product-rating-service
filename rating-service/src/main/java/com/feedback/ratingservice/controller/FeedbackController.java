package com.feedback.ratingservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.ratingservice.dtos.RatingResponseDto;
import com.feedback.ratingservice.payload.ApiResponse;
import com.feedback.ratingservice.service.FeedbackAggregationService;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackAggregationService feedbackAggregationService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getFeedbackWithUser(@PathVariable int productId) {
        try {
            List<RatingResponseDto> responseList = feedbackAggregationService.getRatingsWithUserByProduct(productId);
            ApiResponse response = new ApiResponse("Aggregated feedback fetched successfully.", true, HttpStatus.OK.value(), responseList,LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse("Failed to fetch feedback: " + ex.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null,LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
