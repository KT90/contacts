package com.example.contacts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Code name is required")
    private String codeName;
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;
}
