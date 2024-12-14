package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }

    @Override
    public Account saveAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Integer id, Account account) {
        if (accountRepository.existsById(id)) {
            if (account.getUsername() == null || account.getUsername().isBlank()) {
                throw new IllegalArgumentException("Username cannot be blank");
            }
            if (account.getPassword() == null || account.getPassword().length() < 4) {
                throw new IllegalArgumentException("Password must be at least 4 characters long");
            }
            account.setAccountId(id); // Ensure the ID is preserved
            return accountRepository.save(account);
        }
        throw new IllegalArgumentException("Account with the given ID does not exist");
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account authenticate(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }
        Account account = getAccountByUsername(username);
        if (account == null || !account.getPassword().equals(password)) {
            return null; // or throw a custom exception for better control
        }
        return account;
    }

}