package com.feedback.ratingservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private boolean status;
    private int statusCode;
    private T data;
    private LocalDateTime timestamp;

   
}
