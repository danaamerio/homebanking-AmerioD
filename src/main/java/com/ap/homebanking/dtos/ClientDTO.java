package com.ap.homebanking.dtos;
import com.ap.homebanking.models.Client;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
    }

    public static List<ClientDTO> mapToDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientDTO::new)
                .collect(toList());
    }


}
