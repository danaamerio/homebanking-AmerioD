package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Client;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<ClientLoanDTO> loans;
    private Set<AccountDTO> accounts;
    private Set<CardDTO> cards;
    private String password;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.loans = client.getLoans().stream().map(clientLoan -> new ClientLoanDTO(clientLoan))
                .collect(Collectors.toSet());
        this.accounts = client.getAccounts().stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(card -> new CardDTO(card))
                .collect(Collectors.toSet());
        this.password = client.getPassword();
    }


    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static List<ClientDTO> mapToDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientDTO::new)
                .collect(toList());
    }
}