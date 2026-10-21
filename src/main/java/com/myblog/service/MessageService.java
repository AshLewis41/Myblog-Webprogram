package com.myblog.service;

import com.myblog.controller.request;
import com.myblog.model.Message;
import com.myblog.model.MessageRequest;
import com.myblog.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository=messageRepository;
    }

    public Message create(MessageRequest request){
        if (request.content==null || request.content.isBlank()){
            throw new IllegalArgumentException("content must not be empty");
        }

        Message message = new Message();
        message.setContent(request.content);
        return messageRepository.save(message);
    }
    public List<Message> list() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }





}
