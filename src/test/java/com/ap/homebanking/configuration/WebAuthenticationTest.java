/*
package com.ap.homebanking.configuration;

import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class WebAuthenticationTest {

    @Autowired
    private ClientRepository clientRepository;
    @Test
    void passwordEncoder()throws Exception {

        auth.userDetailsService(inputName -> {
            Client client = clientRepository.findByEmail(inputName);
            if (client != null) {
                if (client.getEmail().equals("@admin.com")) {
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENT"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}*/
