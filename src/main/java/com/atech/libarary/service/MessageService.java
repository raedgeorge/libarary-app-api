package com.atech.libarary.service;

import com.atech.libarary.dao.MessageRepository;
import com.atech.libarary.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author raed abu Sa'da
 * on 16/05/2023
 */

@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void postMessage(Message messageRequest, String userEmail){

        Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);

        messageRepository.save(message);
    }
}
