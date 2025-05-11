package com.feedback.ratingservice.service;

import com.feedback.ratingservice.dtos.RatingResponseDto;
import java.util.List;

public interface FeedbackAggregationService {
    List<RatingResponseDto> getRatingsWithUserByProduct(int productId);
}
