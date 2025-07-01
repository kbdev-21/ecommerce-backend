package com.example.server.mapper;

import com.example.server.common.helper.StringHelper;
import com.example.server.dto.customer.CreateCustomerDto;
import com.example.server.dto.customer.ResponseCustomerDto;
import com.example.server.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public Customer fromCreateDto(CreateCustomerDto createCustomerDto) {
        Customer newCustomer = modelMapper.map(createCustomerDto, Customer.class);

        String lastName = newCustomer.getLastName() == null ? "" : newCustomer.getLastName() + " ";
        String searchName = StringHelper.normalizedVietnameseText(
                lastName + newCustomer.getFirstName()
        );
        newCustomer.setId(UUID.randomUUID());
        newCustomer.setSearchName(searchName);
        newCustomer.setCreatedAt(new Date());
        newCustomer.setUpdatedAt(new Date());

        return newCustomer;
    }

    public ResponseCustomerDto toResponseDto(Customer customer) {
        return modelMapper.map(customer, ResponseCustomerDto.class);
    }
}
