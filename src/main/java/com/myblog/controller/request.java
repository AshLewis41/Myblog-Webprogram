package com.myblog.controller;

import com.myblog.model.MessageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/request")          // ← 新增：类级前缀
public class request {

    private final List<MessageRequest> messages = new ArrayList<>();

    @PostMapping("/messages")
    public MessageRequest create(@RequestBody MessageRequest request) {
        messages.add(request);
        return request;
    }

    @GetMapping("/messages")          // ← 去掉缺省的斜杠，改为标准写法
    public List<MessageRequest> list() {
        return messages;
    }

    @GetMapping("/messages/{index}")
    public MessageRequest getOne(@PathVariable int index) {
        return messages.get(index);
    }
}