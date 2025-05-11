package com.feedback.productservice.payload;

import lombok.Data;

@Data

public class ApiResponse {
    private String message;
    private boolean status;
    private int statusCode;
    private Object data;

    public ApiResponse(String message, boolean status, int statusCode, Object data) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }

    
}
