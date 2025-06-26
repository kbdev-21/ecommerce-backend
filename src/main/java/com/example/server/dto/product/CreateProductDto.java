package com.example.server.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    private List<CreateProductDtoItem> items;
}




