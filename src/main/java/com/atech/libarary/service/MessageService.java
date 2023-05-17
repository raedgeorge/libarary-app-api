package com.atech.libarary.service;

import com.atech.libarary.dao.MessageRepository;
import com.atech.libarary.entity.Message;
import com.atech.libarary.requestmodels.AdminQuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public void updateMessage(AdminQuestionRequest request, String userEmail) throws Exception{

        Optional<Message> optionalMessage = messageRepository.findById(request.getId());

        if (!optionalMessage.isPresent()){
            throw new Exception("no message available.");
        }

        Message message = optionalMessage.get();

        message.setResponse(request.getResponse());
        message.setAdminEmail(userEmail);
        message.setClosed(true);

        messageRepository.save(message);
    }
}
