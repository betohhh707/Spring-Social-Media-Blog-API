package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository; 
    private MessageRepository messageRepository;

    @Autowired
    public AccountService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public Account registerUser(AccountRepository newUser){
        return accountRepository.save(newUser);
    }

}
