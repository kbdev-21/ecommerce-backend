package com.example.server.controller;

import com.example.server.common.record.ApiResponse;
import com.example.server.dto.customer.CreateCustomerDto;
import com.example.server.dto.customer.ResponseCustomerDto;
import com.example.server.entity.Customer;
import com.example.server.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/api/v1/customers")
    public ApiResponse<ResponseCustomerDto> createCustomer(
            @Valid @RequestBody CreateCustomerDto createCustomerDto
    ) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Customer created successfully.",
                customerService.createCustomer(createCustomerDto)
        );
    }

    @GetMapping("/api/v1/customers")
    public ApiResponse<List<ResponseCustomerDto>> getCustomers() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Customers retrieved successfully.",
                customerService.getCustomers()
        );
    }
}
