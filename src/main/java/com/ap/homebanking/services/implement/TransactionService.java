package com.ap.homebanking.services.implement;

import com.ap.homebanking.models.Transaction;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Object> createTransaction(
            double amount,
            String description,
            String accountFromNumber,
            String accountToNumber,
            String email
    );

    void saveTransaction(Transaction transaction);
}
