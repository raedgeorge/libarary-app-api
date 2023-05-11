package com.atech.libarary.service;

import com.atech.libarary.dao.ReviewRepository;
import com.atech.libarary.entity.Review;
import com.atech.libarary.requestmodels.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;


/**
 * @author raed abu Sa'da
 * on 12/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {

        Review validateReview = reviewRepository.findByBookIdAndUserEmail(reviewRequest.getBookId(), userEmail);
        if (validateReview != null){
            throw new Exception("already has a review.");
        }

        Review review = Review.builder()
                .bookId(reviewRequest.getBookId())
                .rating(reviewRequest.getRating())
                .userEmail(userEmail)
                .creationDate(Date.valueOf(LocalDate.now()))
                .reviewDescription(reviewRequest.getReviewDescription().map(Object::toString).orElse(null))
                .build();

        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long bookId){

        Review validateReview = reviewRepository.findByBookIdAndUserEmail(bookId, userEmail);

        return validateReview != null;

    }

}
