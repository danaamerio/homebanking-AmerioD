package com.ap.homebanking.repositories;
import com.ap.homebanking.dtos.ClientDTO;
import com.ap.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<ClientDTO> findByEmail(String email);


}
