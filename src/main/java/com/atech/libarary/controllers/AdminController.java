package com.atech.libarary.controllers;

import com.atech.libarary.requestmodels.AddBookRequest;
import com.atech.libarary.service.AdminService;
import com.atech.libarary.utils.ExtractJWT;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author raed abu Sa'da
 * on 19/05/2023
 */

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
}
