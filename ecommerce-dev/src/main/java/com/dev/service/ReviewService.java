package com.dev.service;

import com.dev.model.Product;
import com.dev.model.Review;
import com.dev.model.User;
import com.dev.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating , Long userId) throws Exception;
    void deleteReview(Long review , Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
