package com.example.server.dto.customer;

import com.example.server.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResponseCustomerDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String searchName;
    private String email;
    private String phoneNum;
    private String address;
    private Date createdAt;
    private Date updatedAt;
}
