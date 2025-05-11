package com.feedback.ratingservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.ratingservice.dtos.RatingResponseDto;
import com.feedback.ratingservice.dtos.UserDto;
import com.feedback.ratingservice.entity.Rating;
import com.feedback.ratingservice.payload.ApiResponse;
import com.feedback.ratingservice.repository.RatingRepository;
import com.feedback.ratingservice.service.FeedbackAggregationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeedbackAggregationServiceImpl implements FeedbackAggregationService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<RatingResponseDto> getRatingsWithUserByProduct(int productId) {
        List<Rating> ratings = ratingRepository.findByProductId(productId);

        return ratings.stream().map(rating -> {
            String userServiceUrl = "http://localhost:8080/api/v1/users/" + rating.getUserId();
            try {
                log.info("Fetching user for ID {}", rating.getUserId());
                ResponseEntity<ApiResponse<UserDto>> response = restTemplate.exchange(
                    userServiceUrl,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<UserDto>>() {}
                );

                log.info(response.getBody().getMessage());
                UserDto user = response.getBody().getData();

                return new RatingResponseDto(
                    rating.getId(),
                    rating.getProductId(),
                    rating.getUserId(),
                    rating.getRating(),
                    rating.getFeedbackText(),
                    user
                );
            } catch (Exception e) {
                log.error("Error fetching user details for userId {}: {}", rating.getUserId(), e.getMessage());
                return new RatingResponseDto(
                    rating.getId(),
                    rating.getProductId(),
                    rating.getUserId(),
                    rating.getRating(),
                    rating.getFeedbackText(),
                    null
                );
            }
        }).collect(Collectors.toList());
    }

}
