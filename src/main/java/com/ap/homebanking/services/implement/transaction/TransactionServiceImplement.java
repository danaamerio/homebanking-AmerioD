package com.ap.homebanking.services.implement.transaction;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public ResponseEntity<Object> createTransaction(
            double amount,
            String description,
            String accountFromNumber,
            String accountToNumber,
            String email
    ) {

        Client authClient = clientRepository.findByEmail(email);
        Account clientAccountsTo = accountRepository.findByNumber(accountToNumber);

        return new ResponseEntity<>("Transaction created successfully", HttpStatus.OK);
    }
}
