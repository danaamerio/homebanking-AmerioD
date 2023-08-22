package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<ClientLoan> loans;
    private Set<Card> cards;
    private String password;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.loans = client.getLoans();
        this.cards = client.getCards();
        this.password = client.getPassword();
    }

    public static List<ClientDTO> mapToDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientDTO::new)
                .collect(toList());
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

    public Set<ClientLoan> getLoans() {
        return loans;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public String getPassword() {
        return password;
    }
}
