package com.example.server.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class UpdateProductGeneralDto {
    @Length(min = 6, max = 50)
    private String name;

    @Length(min = 1, max = 20)
    private String brand;

    @Length(min = 1, max = 20)
    private String category;

    @Length(min = 10)
    private String description;

    @Size(min = 1, max = 20)
    private List<String> imgUrls;

    private Boolean isPublished;
}
