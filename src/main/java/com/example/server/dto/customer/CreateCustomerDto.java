package com.example.server.dto.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCustomerDto {
    @NotBlank
    private String firstName;

    private String lastName;

    private String email;

    @NotBlank
    private String phoneNum;

    private String address;
}
