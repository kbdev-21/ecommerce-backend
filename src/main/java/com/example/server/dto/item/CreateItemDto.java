package com.example.server.dto.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Min(0)
    private int stock;

    @Valid
    private List<CreateItemDtoSpec> specs;



    @Data
    public static class CreateItemDtoSpec {
        @NotBlank
        @Length(max = 20)
        private String key;

        @NotBlank
        @Length(max = 200)
        private String value;
    }
}
