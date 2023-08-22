package com.ap.homebanking.controllers;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping ("/api")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @RequestMapping ("/api/loans")
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }
}