package com.example.server.dto.item;

import com.example.server.dto.product.ResponseProductDto;
import com.example.server.dto.spec.ResponseSpecDto;
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
public class ResponseItemDto {
    private UUID id;

    private String productCode;

    private String fullName;

    private String sku;

    private String variant;

    private List<String> imgUrls;

    private BigDecimal price;

    private int stock;

    private int sold;

    private List<ResponseSpecDto> specs;

    private Date createdAt;

    private Date updatedAt;
}
