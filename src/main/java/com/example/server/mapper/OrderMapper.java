package com.example.server.mapper;

import com.example.server.dto.order.CreateOrderDto;
import com.example.server.dto.order.ResponseOrderDto;
import com.example.server.entity.Customer;
import com.example.server.entity.Item;
import com.example.server.entity.Order;
import com.example.server.entity.OrderDetail;
import com.example.server.exception.CustomException;
import com.example.server.repository.CustomerRepository;
import com.example.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public ResponseOrderDto toResponseDto(Order order) {
        return modelMapper.map(order, ResponseOrderDto.class);
    }

    public Order fromCreateDto(CreateOrderDto createOrderDto) {
        Order newOrder = modelMapper.map(createOrderDto, Order.class);

        List<OrderDetail> details = createOrderDto.getDetails()
                .stream()
                .map(orderDetail -> {
                    Item item = itemRepository.findBySku(
                            orderDetail.getItemSku()).orElseThrow(
                                    () -> new CustomException(HttpStatus.NOT_FOUND, "Item not found.")
                    );

                    OrderDetail detail = modelMapper.map(orderDetail, OrderDetail.class);

                    BigDecimal price = item.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity()));

                    detail.setId(UUID.randomUUID());
                    detail.setPrice(price);
                    detail.setOrder(newOrder);
                    detail.setItem(item);
                    detail.setCreatedAt(new Date());
                    detail.setUpdatedAt(new Date());
                    return detail;
                })
                .toList();

        Customer customer = customerRepository
                .findById(createOrderDto.getCustomerId()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "Item not found.")
                );

        newOrder.setId(UUID.randomUUID());
        newOrder.setDetails(details);
        newOrder.setCustomer(customer);
        newOrder.setCreatedAt(new Date());
        newOrder.setUpdatedAt(new Date());

        return newOrder;
    }
}
