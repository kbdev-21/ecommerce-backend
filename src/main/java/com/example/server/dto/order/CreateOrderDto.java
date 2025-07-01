package com.example.server.dto.order;

import com.example.server.common.enums.OrderMethod;
import com.example.server.common.enums.OrderStatus;
import com.example.server.entity.Customer;
import com.example.server.entity.Item;
import com.example.server.entity.Order;
import com.example.server.entity.OrderDetail;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateOrderDto {
    @NotNull
    private OrderMethod method;

    @NotNull
    private OrderStatus status;

    @NotNull
    private UUID customerId;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<CreateOrderDtoDetail> details;

    @Data
    public static class CreateOrderDtoDetail {
        @NotNull
        private String itemSku;

        @NotNull
        @Min(1)
        private int quantity;
    }
}
