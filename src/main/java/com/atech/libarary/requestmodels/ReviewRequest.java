package com.atech.libarary.requestmodels;

import lombok.Data;

import java.util.Optional;

/**
 * @author raed abu Sa'da
 * on 12/05/2023
 */

@Data
public class ReviewRequest {

    private double rating;
    private Long bookId;
    private Optional<String> reviewDescription;
}
