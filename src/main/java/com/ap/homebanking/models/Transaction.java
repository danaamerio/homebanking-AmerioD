// Clase Transaction
package com.ap.homebanking.models;

import com.ap.homebanking.Enum.TransactionType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime dateTime;

    @Column(name = "current_balance")
    private Double currentBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    public Transaction() {
    }

    public Transaction(TransactionType type, Double amount, String description, LocalDateTime dateTime, Double currentBalance) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.currentBalance = currentBalance;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
