package com.atlas.reviewms.review;


import com.atlas.reviewms.review.impl.ReviewServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
GET /companies/{companyId}/reviews
POST /companies/{companyId}/reviews
GET /companies/{companyId}/reviews/{reviewId}
PUT /companies/{companyId}/reviews/update/{reviewId}
DELETE /companies/{companyId}/reviews/delete/{reviewId}

Path Variable is a variable mentioned in URL. See above URLs for reference

Request Parameter is a variable mentioned with URL usually after ?. See below URLs for reference

Microservice updated URLs
GET /reviews?companyId={companyId}
POST /reviews?companyId={companyId}
GET /reviews/{reviewId}
PUT /reviews/update/{reviewId}
DELETE /reviews/delete/{reviewId}
 */

@RestController
@RequestMapping("/reviews")
public class ReviewControllers {

    private ReviewServices reviewServices;

    public ReviewControllers(ReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewServices.getReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean addStatus = reviewServices.addReview(companyId, review);
        if (!addStatus){
            return new ResponseEntity<>("Review not saved -> Invalid JSON or Company ID not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("New Review Added", HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewByID(@PathVariable Long reviewId){
        Review review = reviewServices.getReviewById(reviewId);
        if (review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        boolean updateStatus = reviewServices.updateReview(reviewId, review);
        if(!updateStatus){
            return new ResponseEntity<>("Review not updated -> Invalid JSON or Company ID not found or Review ID not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Review updated with ID: "+reviewId, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean deleteStatus = reviewServices.deleteReview(reviewId);

        if(!deleteStatus){
            return new ResponseEntity<>("Review not deleted -> Company ID not found or Review ID not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Review deleted with ID: " +reviewId, HttpStatus.OK);
    }
}
