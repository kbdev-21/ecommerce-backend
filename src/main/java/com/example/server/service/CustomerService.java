package com.example.server.service;

import com.example.server.dto.customer.CreateCustomerDto;
import com.example.server.dto.customer.ResponseCustomerDto;

import java.util.List;

public interface CustomerService {
    ResponseCustomerDto createCustomer(CreateCustomerDto createCustomerDto);

    List<ResponseCustomerDto> getCustomers();
}
