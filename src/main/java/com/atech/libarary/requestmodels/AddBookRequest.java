package com.atech.libarary.requestmodels;

import lombok.Data;

/**
 * @author raed abu Sa'da
 * on 19/05/2023
 */

@Data
public class AddBookRequest {

    private String title;
    private String author;
    private String description;
    private int copies;
    private String category;
    private String img;
}
