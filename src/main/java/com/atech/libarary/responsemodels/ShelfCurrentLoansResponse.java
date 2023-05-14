package com.atech.libarary.responsemodels;

import com.atech.libarary.entity.Book;
import lombok.Data;

/**
 * @author raed abu Sa'da
 * on 14/05/2023
 */

@Data
public class ShelfCurrentLoansResponse {

    private Book book;
    private int daysLeft;
}
