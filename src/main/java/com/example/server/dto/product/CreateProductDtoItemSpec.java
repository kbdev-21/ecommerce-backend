package com.example.server.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateProductDtoItemSpec {
    @NotBlank
    @Length(max = 20)
    private String key;

    @NotBlank
    @Length(max = 200)
    private String value;
}
