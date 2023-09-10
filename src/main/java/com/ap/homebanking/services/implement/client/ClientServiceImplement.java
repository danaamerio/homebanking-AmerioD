package com.ap.homebanking.services.implement.client;

import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
   ClientRepository clientRepository;


    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return (client != null) ? new ClientDTO(client) : null;
    }

    @Override
    public ClientDTO getClientCurrent(String email) {
        return new ClientDTO(clientRepository.findByEmail(email));
    }

    @Override
    public boolean findByEmail(String email) {
        return false;
    }

    @Override
    public void save(Client clientnew) {
        clientRepository.save(clientnew);

    }

    @Override
    public ClientDTO getClientDTOByEmail(String name) {
        String email = null;
        return new ClientDTO(clientRepository.findByEmail(email));
    }

}
