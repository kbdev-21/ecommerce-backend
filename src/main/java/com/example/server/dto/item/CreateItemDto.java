package com.example.server.dto.item;

import com.example.server.dto.product.CreateProductDtoItemSpec;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateItemDto {
    @NotBlank
    private String productCode;

    @NotBlank
    @Length(max = 20)
    private String sku;

    @NotBlank
    @Length(max = 20)
    private String variant;

    @NotNull
    @Size(min = 1, max = 20)
    private List<String> imgUrls;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int stock;

    private List<CreateItemDtoSpec> specs;
}
