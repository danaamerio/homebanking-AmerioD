package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.Enum.TransactionType;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime dateTime;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.dateTime = transaction.getDateTime();
    }

    public static List<TransactionDTO> mapToDTOList(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionDTO::new)
                .collect(toList());
    }
}