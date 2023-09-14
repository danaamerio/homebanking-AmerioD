package com.ap.homebanking.controllers;

import com.ap.homebanking.Enum.AccountType;
import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.services.implement.AccountService;
import com.ap.homebanking.services.implement.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping(value = "clients/current/accounts")
    public ResponseEntity<Object> addAccount(
            @RequestParam double initialBalance,
            @RequestParam String accountType, // Tipo de cuenta seleccionado (CORRIENTE o AHORRO)
            Authentication authentication) {

        Client client = clientService.findByEmail(authentication.getName());

        if (client.getAccounts().size() < 3) {
            if (initialBalance <= 0 || accountType.isBlank()) {
                return new ResponseEntity<>("Datos faltantes o inválidos", HttpStatus.BAD_REQUEST);
            }

            // Convierte el tipo de cuenta en una enumeración AccountType
            AccountType type = AccountType.valueOf(accountType.toUpperCase());

            Account account = new Account("VIN-" + getRandomNumber(1000000, 99999999), LocalDate.now(), initialBalance);
            account.setAccountType(type);
            client.addAccounts(account);
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}


