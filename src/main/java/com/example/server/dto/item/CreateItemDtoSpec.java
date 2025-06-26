package com.example.server.dto.item;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateItemDtoSpec {
    @NotBlank
    @Length(max = 20)
    private String key;

    @NotBlank
    @Length(max = 200)
    private String value;
}
