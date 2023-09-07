package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.dtos.ClientRequestDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.services.implement.account.AccountService;
import com.ap.homebanking.services.implement.card.CardService;
import com.ap.homebanking.services.implement.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping ("/api")
public class ClientController {

   // @Autowired
    //private ClientRepository clientRepository;
    //@Autowired
    //private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/clients")
    public  List<ClientDTO> getClients(){
        return clientService.getAllClients();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/clients")

    public ResponseEntity<Object> register(@RequestBody ClientRequestDTO clientRequestDTO) {


        if (clientRequestDTO.getFirstName().isEmpty() || clientRequestDTO.getLastName().isEmpty() || clientRequestDTO.getEmail().isEmpty() || clientRequestDTO.getLoan().isEmpty() || clientRequestDTO.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }


        if (!clientService.findByEmail(clientRequestDTO.getEmail())) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }


        Client clientnew = new Client(clientRequestDTO.getFirstName(),
                                clientRequestDTO.getLastName(), clientRequestDTO.getEmail(),
                                clientRequestDTO.getLoan(), clientRequestDTO.getPassword(),
                                clientRequestDTO.getRole());
        clientService.save(clientnew);


        Account accountnew = new Account("VIN-" + getRandomNumber(1000000, 99999999), LocalDate.now(),0.00);
        clientnew.addAccounts(accountnew);

        accountService.save(accountnew);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    private int getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("El valor mínimo debe ser menor que el valor máximo");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


    @RequestMapping("/clients/current")
    public ClientDTO getClientCurrent(Authentication authentication) {
       return clientService.getClientDTOByEmail(authentication.getName());
    }




}