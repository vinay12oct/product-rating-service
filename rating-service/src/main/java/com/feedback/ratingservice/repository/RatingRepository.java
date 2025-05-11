package com.feedback.ratingservice.repository;

import com.feedback.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(int userId);
    List<Rating> findByProductId(int productId);
}
