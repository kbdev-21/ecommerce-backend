package com.example.server.service.impl;

import com.example.server.dto.customer.CreateCustomerDto;
import com.example.server.dto.customer.ResponseCustomerDto;
import com.example.server.entity.Customer;
import com.example.server.mapper.CustomerMapper;
import com.example.server.repository.CustomerRepository;
import com.example.server.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public ResponseCustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer newCustomer = customerMapper.fromCreateDto(createCustomerDto);
        Customer savedCustomer = customerRepository.save(newCustomer);
        return customerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public List<ResponseCustomerDto> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .toList();
    }
}