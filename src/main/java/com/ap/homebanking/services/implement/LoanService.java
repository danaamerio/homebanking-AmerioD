package com.ap.homebanking.services.implement;

import com.ap.homebanking.dtos.LoanApplicationDTO;
import com.ap.homebanking.models.Loan;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface LoanService {
    ResponseEntity<String> addLoan(LoanApplicationDTO loanApplicationDTO);
    ResponseEntity<Object> createLoan(LoanApplicationDTO loanApplicationDTO, String email, Long id);
    ResponseEntity<Object> getLoan(Long id);

    Optional<Loan> findById(Long id);

    void save(Loan loan);

    Optional<Object> findAll();
}
