package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.dtos.ClientRequestDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @RequestMapping("/clients")
    public  List<ClientDTO> getClients(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/clients")

    public ResponseEntity<Object> register(@RequestBody ClientRequestDTO clientRequestDTO) {


        if (clientRequestDTO.getFirstName().isEmpty() || clientRequestDTO.getLastName().isEmpty() || clientRequestDTO.getEmail().isEmpty() || clientRequestDTO.getLoan().isEmpty() || clientRequestDTO.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }


        if (clientRepository.findByEmail(clientRequestDTO.getEmail()) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Client clientnew = new Client(clientRequestDTO.getFirstName(),
                                clientRequestDTO.getLastName(), clientRequestDTO.getEmail(),
                                clientRequestDTO.getLoan(), clientRequestDTO.getPassword(),
                                clientRequestDTO.getRole());
        clientRepository.save(clientnew);


        Account accountnew = new Account("VIN-" + getRandomNumber(1000000, 99999999), LocalDate.now(),0.00);
        clientnew.addAccounts(accountnew);
        accountRepository.save(accountnew);

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
        ClientDTO clientDTO = new ClientDTO(clientRepository
                .findByEmail(authentication.getName()));
        return clientDTO;
    }




}