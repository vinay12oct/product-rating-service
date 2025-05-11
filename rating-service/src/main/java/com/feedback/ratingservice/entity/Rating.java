package com.feedback.ratingservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int productId;

    @Column(nullable = false)
    private int rating; // 1 to 5

    private String feedbackText;
    private String imageUrl; // Optional

    private LocalDateTime createdAt = LocalDateTime.now();
}
