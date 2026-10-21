package com.myblog.controller;

import com.myblog.model.Message;
import com.myblog.model.MessageRequest;
import com.myblog.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public Message create(@RequestBody MessageRequest request){
        return messageService.create(request);
    }

    @GetMapping("/messages")
    public List<Message> list(@RequestParam(required = false) String keyword){
        List<Message> all = messageService.list();

        if (keyword == null){
            return all;
        }

        List<Message> result = new ArrayList<>();
        for (int i = 0; i<all.size(); i++){
            Message m = all.get(i);
            if (m.getContent().contains(keyword)){
                result.add(m);
            }
        }

        return result;

    }

}
