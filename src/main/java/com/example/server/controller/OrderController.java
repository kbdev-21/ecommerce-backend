package com.example.server.controller;

import com.example.server.common.record.ApiResponse;
import com.example.server.dto.order.CreateOrderDto;
import com.example.server.dto.order.ResponseOrderDto;
import com.example.server.service.OrderService;
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
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public ApiResponse<ResponseOrderDto> createOrder(@Valid @RequestBody CreateOrderDto dto) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Order created successfully.",
                orderService.createOrder(dto)
        );
    }

    @GetMapping("/api/v1/orders")
    public ApiResponse<List<ResponseOrderDto>> getAllOrders() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Orders retrieved successfully.",
                orderService.getOrders()
        );
    }
}
