package com.ap.homebanking.services.implement.account;

import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDTO> getAccounts();

    AccountDTO getAccount(Long id);

    ResponseEntity<Object> addAccount(Authentication authentication);

    <T> Optional<T> findById(Long id);

    void save(Account account1);

    Arrays findAll();

    Account findByNumber(String toAccountNumber);
}




