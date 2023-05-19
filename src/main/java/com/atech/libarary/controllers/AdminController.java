package com.atech.libarary.controllers;

import com.atech.libarary.requestmodels.AddBookRequest;
import com.atech.libarary.service.AdminService;
import com.atech.libarary.utils.ExtractJWT;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader("Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null){
            throw new Exception(("Admins Only"));
        }

        adminService.postBook(addBookRequest);
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader("Authorization") String token, @RequestParam Long bookId) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        // check if userEmail is admin or has admin role. if not throw exception.

        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader("Authorization") String token, @RequestParam Long bookId) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        // check if userEmail is admin or has admin role. if not throw exception.

        adminService.decreaseBookQuantity(bookId);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader("Authorization") String token, @RequestParam Long bookId) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        // check if userEmail is admin or has admin role. if not throw exception.
        adminService.deleteBookById(bookId);
    }
}
