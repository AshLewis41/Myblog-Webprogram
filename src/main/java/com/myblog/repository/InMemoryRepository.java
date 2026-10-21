//package com.myblog.repository;
//
//import com.myblog.model.Message;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryRepository implements MessageRepository {
//    private final List<Message> messages = new ArrayList<>();
//
//    public Message save(Message message) {
//        return message;
//    }
//
//    public List<Message> findAll() {
//        return messages;
//    }
//
//    public Optional<Message> findById(Long id) {
//        return messages.stream().filter(m -> m.getId().equals(id)).findFirst();
//    }
//
//}
