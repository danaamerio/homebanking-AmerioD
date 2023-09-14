package com.ap.homebanking.services.implement;

import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccount(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return (account != null) ? new AccountDTO(account) : null;
    }

    @Override
    public ResponseEntity<Object> addAccount(Authentication authentication) {

        return null;
    }

    @Override
    public <T> Optional<T> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Account account1) {
        accountRepository.save(account1);
    }

    @Override
    public Arrays findAll() {
        return null;
    }

    @Override
    public Account findByNumber(String toAccountNumber) {
        return null;
    }
}