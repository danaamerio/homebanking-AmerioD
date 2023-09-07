package com.ap.homebanking.services.implement.loan;

import com.ap.homebanking.dtos.LoanApplicationDTO;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ResponseEntity<String> addLoan(LoanApplicationDTO loanApplicationDTO) {
        Loan loan = new Loan();
        loan.setAmount(loanApplicationDTO.getAmount());

        loanRepository.save(loan);

        return new ResponseEntity<>("Solicitud de préstamo guardada con éxito", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createLoan(LoanApplicationDTO loanApplicationDTO, String email, Long id) {
        Client client = clientRepository.findByEmail(email);
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return null;
    }

    @Override
    public ResponseEntity<Object> getLoan(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return null;
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Loan loan) {

    }

    @Override
    public Optional<Object> findAll() {
        return Optional.empty();
    }
}
