package com.atech.libarary.controllers;

import com.atech.libarary.requestmodels.ReviewRequest;
import com.atech.libarary.service.ReviewService;
import com.atech.libarary.utils.ExtractJWT;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author raed abu Sa'da
 * on 12/05/2023
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
@CrossOrigin("https://localhost:5173")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader("Authorization") String token,
                                    @RequestParam ("bookId") Long bookId) throws Exception{

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null){
            throw new Exception("user email is missing!");
        }

        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader("Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null){
            throw new Exception("user email is missing!");
        }

        reviewService.postReview(userEmail, reviewRequest);
    }
}
