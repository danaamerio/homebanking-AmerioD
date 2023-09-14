// Controlador TransactionController
package com.ap.homebanking.controllers;

import com.ap.homebanking.Enum.TransactionType;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.services.implement.AccountService;
import com.ap.homebanking.services.implement.ClientService;
import com.ap.homebanking.services.implement.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class TransactionController {

/*    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;*/

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam String accountFromNumber,
            @RequestParam String accountToNumber,
            Authentication authentication) {

        Client authClient = clientService.findByEmail(authentication.getName());

        if (amount <= 0 || description.isBlank() || accountFromNumber.isBlank() || accountToNumber.isBlank()) {
            return new ResponseEntity<>("Missing or invalid data", HttpStatus.BAD_REQUEST);
        }

        if (accountFromNumber.equals(accountToNumber)) {
            return new ResponseEntity<>("Source and destination accounts are the same", HttpStatus.FORBIDDEN);
        }

        Account clientAccountsTo = accountService.findByNumber(accountToNumber);

        if (clientAccountsTo == null) {
            return new ResponseEntity<>("Invalid destination account", HttpStatus.BAD_REQUEST);
        }

        if (clientAccountsTo.getBalance() < amount) {
            return new ResponseEntity<>("Insufficient funds in the destination account", HttpStatus.BAD_REQUEST);
        }

        /////////////BALANCE AJUSTABLE////////////
        // Calcula el saldo actual
        Double currentBalance = accountService.findByNumber(accountFromNumber).getBalance() - amount;

        // Crea la transacción con el saldo actual calculado


        Transaction transaction = new Transaction(TransactionType.DEBIT, amount, description, LocalDateTime.now(), currentBalance);
        transaction.setAccount(clientAccountsTo);

        // Guarda la transacción en la base de datos
        transactionService.saveTransaction(transaction);

        // Actualiza el saldo de la cuenta origen
        Account accountFrom = accountService.findByNumber(accountFromNumber);
        accountFrom.setBalance(currentBalance);
        accountService.save(accountFrom);

        // Actualiza el saldo de la cuenta destino
        clientAccountsTo.setBalance(clientAccountsTo.getBalance() + amount);
        accountService.save(clientAccountsTo);

        return new ResponseEntity<>("Transaction created successfully", HttpStatus.OK);
    }
}


