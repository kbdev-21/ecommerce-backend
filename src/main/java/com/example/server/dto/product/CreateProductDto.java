package com.example.server.dto.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductDto {
    @NotBlank
    @Length(max = 20)
    private String code;

    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 20)
    private String brand;

    @NotBlank
    @Length(max = 20)
    private String category;

    @NotBlank
    @Length(min = 10)
    private String description;

    @NotNull
    @Size(min = 1, max = 20)
    private List<String> imgUrls;

    @NotNull
    private boolean isPublished;

    @NotNull
    @Valid
    private List<CreateProductDtoItem> items;



    @Data
    public static class CreateProductDtoItem {
        @NotBlank
        @Length(max = 20)
        private String sku;

        @NotBlank
        @Length(max = 50)
        private String variant;

        @NotNull
        @Size(min = 1, max = 20)
        private List<String> imgUrls;

        @NotNull
        private BigDecimal price;

        @NotNull
        private int stock;

        @Valid
        private List<CreateProductDtoItemSpec> specs;
    }



    @Data
    public static class CreateProductDtoItemSpec {
        @NotBlank
        @Length(max = 20)
        private String key;

        @NotBlank
        @Length(max = 200)
        private String value;
    }
}




