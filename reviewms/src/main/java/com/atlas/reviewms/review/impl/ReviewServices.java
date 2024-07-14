package com.atlas.reviewms.review.impl;

import com.atlas.reviewms.review.Review;

import java.util.List;

public interface ReviewServices {
    List<Review> getReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
}
