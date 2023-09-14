package com.ap.homebanking.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
@SpringBootTest
class ClientTest {

    @Test
    void getId() {
    }

    @Test
    void getFirstName() {
        String resultado = Client.getFirstName();
        assertThat(resultado, any(String.class));
    }

    @Test
    void setFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void getLoan() {
    }

    @Test
    void setLoan() {
    }

    @Test
    void getCards() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void setAccounts() {
    }

    @Test
    void getLoans() {
    }

    @Test
    void setLoans() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void getRole() {
    }

    @Test
    void setRole() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void addClientLoan() {
    }

    @Test
    void addCard() {
    }

    @Test
    void addAccounts() {
    }
}