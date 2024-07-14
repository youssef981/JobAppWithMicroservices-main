package com.atlas.reviewms.review.impl;


import com.atlas.reviewms.review.Review;
import com.atlas.reviewms.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServicesImplementation implements ReviewServices{

    ReviewRepository reviewRepo;

    public ReviewServicesImplementation(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepo.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
