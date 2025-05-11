package com.myapp.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("User Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/productServiceFallback")
    public ResponseEntity<String> productServiceFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Product Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/ratingServiceFallback")
    public ResponseEntity<String> ratingServiceFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Rating Service is currently unavailable. Please try again later.");
    }
}
