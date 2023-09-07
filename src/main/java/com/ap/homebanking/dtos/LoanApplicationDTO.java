package com.ap.homebanking.dtos;

public class LoanApplicationDTO {
    private long loanid;
    private String name;
    private  double maxAmount;
    private Integer payment;
    private String accountNumber;

    public long getLoanid() {
        return loanid;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public Integer getPayment() {
        return payment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getPayments() {
        return getPayments();
    }

    public String getToAccountNumber() {
        return getToAccountNumber();
    }

    public Object getAmount() {
        return getAmount();
    }
}
