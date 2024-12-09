package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessageList(){ return (List<Message>)messageRepository.findAll();}

    public Message getMessage(Integer id )throws ResourceNotFoundException{
        return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find. Please check id and try again") ); 
    }

    public void deleteMessage(Integer id){
        messageRepository.deleteById(id);
    }

    public Message patchMessage(Integer id, Message message){
        if(messageRepository.existsById(id)){
            if(message.getMessageText().isEmpty()){
                throw new IllegalArgumentException("text cannot be empty");
            }

            if(message.getMessageText().length()>255){
                throw new IllegalArgumentException("text length exceeds limit");
            }
            return messageRepository.save(message);
        }
        return null;
    }

    public Optional<Message> getMessageFromAUser(Integer id){
        return messageRepository.findById(id);
    }
    public Message createNewMessage(Message message){
        return messageRepository.save(message);
    }
}
