package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private double balance;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
    }

    public static List<AccountDTO> mapToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountDTO::new)
                .collect(toList());
    }



}