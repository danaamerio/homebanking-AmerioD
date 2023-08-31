package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.AccountDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping("/accounts")
    public  List<AccountDTO> getAccounts(){
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

   @RequestMapping(value = "clients/current/accounts",method = RequestMethod.POST)
    public ResponseEntity<Object> addAccount (Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());

       if (client.getAccounts().size()<3){
            Account account1 = new Account("VIN-" + getRandomNumber(1000000, 99999999), LocalDate.now(),0.00);
            client.addAccounts(account1);
            accountRepository.save(account1);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

    }
    public  int getRandomNumber(int min, int max){
    return (int) ((Math.random() * (max - min)) + min);
    }

}


