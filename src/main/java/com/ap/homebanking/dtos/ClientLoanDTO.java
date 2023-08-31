package com.ap.homebanking.dtos;

import com.ap.homebanking.models.ClientLoan;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientLoanDTO {
    private Long id;
    private Double amount;
    private Integer payments;


    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public static List<ClientLoanDTO> mapToDTOList(List<ClientLoan> clientLoans) {
        return clientLoans.stream()
                .map(ClientLoanDTO::new)
                .collect(toList());
    }
}