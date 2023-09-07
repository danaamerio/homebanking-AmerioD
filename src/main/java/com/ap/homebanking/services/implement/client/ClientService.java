package com.ap.homebanking.services.implement.client;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

   ClientDTO getClient(Long id);

    ClientDTO getClientCurrent(String email);


    boolean findByEmail(String email);

    void save(Client clientnew);

    ClientDTO getClientDTOByEmail(String name);
}
