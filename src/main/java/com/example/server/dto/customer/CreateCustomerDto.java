package com.example.server.dto.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCustomerDto {
    @NotBlank
    private String firstName;

    private String lastName;

    private String email;

    @NotBlank
    private String phoneNum;

    private String address;
}
