package com.example.service;

import com.example.entity.Message;

import java.util.List;

import org.springframework.stereotype.Service;

public interface MessageService {
    List<Message> getAllMessages();

    Message getMessageById(Integer id);

    Message saveMessage(Message message);

    Message updateMessage(Integer id, Message message);

    void deleteMessage(Integer id);

    Message createMessage(Message message);

    int deleteMessageById(Integer messageId);

    List<Message> getAllMessagesForUser(Integer userId);

}