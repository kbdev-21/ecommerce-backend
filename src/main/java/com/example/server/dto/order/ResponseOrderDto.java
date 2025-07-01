package com.example.server.dto.order;

import com.example.server.common.enums.OrderMethod;
import com.example.server.common.enums.OrderStatus;
import com.example.server.dto.customer.ResponseCustomerDto;
import com.example.server.dto.item.ResponseItemDto;
import com.example.server.entity.Customer;
import com.example.server.entity.Item;
import com.example.server.entity.Order;
import com.example.server.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseOrderDto {
    private UUID id;
    private BigDecimal totalPrice;
    private OrderMethod method;
    private OrderStatus status;
    private ResponseCustomerDto customer;
    private List<ResponseOrderDtoDetail> details;
    private Date createdAt;
    private Date updatedAt;

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ResponseOrderDtoDetail {
        private UUID id;
        private ResponseItemDto item;
        private int quantity;
        private BigDecimal price;
        private Date createdAt;
        private Date updatedAt;
    }
}
