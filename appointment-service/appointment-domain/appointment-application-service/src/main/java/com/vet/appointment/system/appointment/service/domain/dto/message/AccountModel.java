package com.vet.appointment.system.appointment.service.domain.dto.message;

import java.util.UUID;

public class AccountModel {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    public AccountModel(UUID id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
