package com.atech.libarary.controllers;

import com.atech.libarary.entity.Book;
import com.atech.libarary.service.BookService;
import com.atech.libarary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

/**
 * @author raed abu Sa'da
 * on 09/05/2023
 */

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam("bookId") Long bookId,
                             @RequestHeader(value = "Authorization") String token) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkOutBook(userEmail, bookId);
    }


    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId,
                                      @RequestHeader(value = "Authorization") String token){

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }


    @GetMapping("/secure/currentloans/count")
    public int getLoansCountByUser(@RequestHeader(value = "Authorization") String token){

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }
}
