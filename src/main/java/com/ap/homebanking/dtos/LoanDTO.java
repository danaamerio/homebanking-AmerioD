package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Loan;

public class LoanDTO {
    private Long id;
    private String name;
    private Double maxAmount;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }
}


