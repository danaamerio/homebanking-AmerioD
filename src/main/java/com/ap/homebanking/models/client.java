package com.ap.homebanking.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class client {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;


    public client(){}

    public client(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getId() {
       return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }git

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
