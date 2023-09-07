package com.ap.homebanking.services.implement.transaction;

import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Object> createTransaction(
            double amount,
            String description,
            String accountFromNumber,
            String accountToNumber,
            String email
    );
}
