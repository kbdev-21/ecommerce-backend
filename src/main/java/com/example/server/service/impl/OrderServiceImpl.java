package com.example.server.service.impl;

import com.example.server.dto.order.CreateOrderDto;
import com.example.server.dto.order.ResponseOrderDto;
import com.example.server.entity.Item;
import com.example.server.entity.Order;
import com.example.server.entity.OrderDetail;
import com.example.server.exception.CustomException;
import com.example.server.mapper.OrderMapper;
import com.example.server.repository.ItemRepository;
import com.example.server.repository.OrderRepository;
import com.example.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    @Override
    public ResponseOrderDto createOrder(CreateOrderDto createOrderDto) {
        Order newOrder = orderMapper.fromCreateDto(createOrderDto);

        List<Item> verifiedItems = new ArrayList<>();
        for (OrderDetail detail : newOrder.getDetails()) {
            Item item = detail.getItem();
            if(item.sell(detail.getQuantity())) {
                verifiedItems.add(item);
            }
            else {
                throw new CustomException(HttpStatus.CONFLICT, "Insufficient stock for item: " + item.getSku());
            }
        }
        itemRepository.saveAll(verifiedItems);

        return orderMapper.toResponseDto(orderRepository.save(newOrder));
    }

    @Override
    public List<ResponseOrderDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponseDto)
                .toList();
    }
}
