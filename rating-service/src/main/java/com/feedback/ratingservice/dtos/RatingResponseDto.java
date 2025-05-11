package com.feedback.ratingservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDto {
    private int ratingId;
    private int productId;
    private int userId;
    private double rating;
    private String feedback;

    private UserDto user; 
}
