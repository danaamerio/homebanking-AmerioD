package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.LoanApplicationDTO;
import com.ap.homebanking.dtos.LoanDTO;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.services.implement.AccountService;
import com.ap.homebanking.services.implement.ClientService;
import com.ap.homebanking.services.implement.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

 /*  @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;*/

    @Autowired
    private LoanService loanService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.findAll().stream().map(loan -> new LoanDTO((Loan) loan)).collect(Collectors.toList());
    }


    @PostMapping("/loans")
    public ResponseEntity<String> addLoan(@RequestBody LoanApplicationDTO loanApplicationDTO) {
        Loan loan = new Loan();
        loan.setAmount(loanApplicationDTO.getAmount());

        loanService.save(loan);

        return new ResponseEntity<>("Solicitud de préstamo guardada con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/loans/{id}")
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        Optional<Loan> optionalLoan = loanService.findById(id);

        if (!optionalLoan.isPresent()) {
            return new ResponseEntity<>("El préstamo no existe", HttpStatus.NOT_FOUND);
        }

        Loan loan = optionalLoan.get();
        Account accountToNumber = accountService.findByNumber(loanApplicationDTO.getToAccountNumber());

        if (loanApplicationDTO.getMaxAmount() <= 0 || loanApplicationDTO.getPayments() <= 0) {
            return new ResponseEntity<>("El monto es menor o igual a cero.", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getMaxAmount() > loan.getMaxAmount()) {
            return new ResponseEntity<>("El monto solicitado excede el monto máximo del préstamo", HttpStatus.FORBIDDEN);
        }

        if (accountToNumber == null) {
            return new ResponseEntity<>("La cuenta de destino no existe", HttpStatus.FORBIDDEN);
        }

        if (!client.getAccounts().contains(accountToNumber)) {
            return new ResponseEntity<>("La cuenta de destino no pertenece al cliente autenticado", HttpStatus.FORBIDDEN);
        }

        //////////////////// porcentaje ajustable de prestamo (el 20%)/////////////////////////
        double amount = loanApplicationDTO.getMaxAmount() * 0.2;

        // Crea el objeto ClientLoan
        ClientLoan newLoan = new ClientLoan();
        newLoan.setClient(client);
        newLoan.setLoan(loan);
        newLoan.setAmount(amount);

        // Guarda el préstamo en la base de datos
        client.getLoans().add(newLoan);
        clientService.save(client);

        return new ResponseEntity<>("Préstamo creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<Object> getLoan(@PathVariable Long id) {
        Optional<Loan> optionalLoan = loanService.findById(id);

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Préstamo no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}










