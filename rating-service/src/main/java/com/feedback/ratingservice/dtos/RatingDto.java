package com.feedback.ratingservice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingDto {

    @NotNull
    private int userId;

    @NotNull
    private int productId;

    @Min(1)
    @Max(5)
    private int rating;

    private String feedbackText;
    private String imageUrl;
}
