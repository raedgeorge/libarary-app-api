package com.atech.libarary.controllers;

import com.atech.libarary.entity.Message;
import com.atech.libarary.requestmodels.AdminQuestionRequest;
import com.atech.libarary.service.MessageService;
import com.atech.libarary.utils.ExtractJWT;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author raed abu Sa'da
 * on 16/05/2023
 */

@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader("Authorization") String token, @RequestBody Message message){

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messageService.postMessage(message, userEmail);
    }

    @PutMapping("/secure/update/message")
    public void updateMessage(@RequestHeader("Authorization") String token,
                              @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");

//        if (admin == null || !admin.equals("admin")){
//            throw new Exception("Admin page only.");
//        }

        System.out.println(adminQuestionRequest);

        messageService.updateMessage(adminQuestionRequest, userEmail);
    }
}
