package com.ap.homebanking.dtos;

import com.ap.homebanking.Enum.RoleType;

public class ClientRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String loan;
    private String password;
    private RoleType role;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLoan() {
        return loan;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }
}
