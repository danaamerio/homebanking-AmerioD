package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;
import com.ap.homebanking.models.Loan;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class ClientLoanDTO {
    private Long id;
    private Double amount;
    private Integer payments;
    private Client client;
    private Loan loan;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.client = clientLoan.getClient();
        this.loan = clientLoan.getLoan();
    }

    public static List<ClientLoanDTO> mapToDTOList(List<ClientLoan> clientLoans) {
        return clientLoans.stream()
                .map(ClientLoanDTO::new)
                .collect(toList());
    }
}