package com.ap.homebanking.repositories;

import com.ap.homebanking.models.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface clientRepository extends JpaRepository<client,String> {
}
