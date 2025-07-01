package com.example.server.service;

import com.example.server.dto.order.CreateOrderDto;
import com.example.server.dto.order.ResponseOrderDto;

import java.util.List;

public interface OrderService {
    ResponseOrderDto createOrder(CreateOrderDto createOrderDto);

    List<ResponseOrderDto> getOrders();
}
