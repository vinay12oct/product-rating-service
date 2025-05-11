package com.feedback.userservice.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;
    private int statusCode;
    private Object data;
    private LocalDateTime timestamp;

    // constructor, getters, setters
}


   


