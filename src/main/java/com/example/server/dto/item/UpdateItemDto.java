package com.example.server.dto.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class UpdateItemDto {
    @Length(max = 20)
    private String variant;

    @Size(min = 1, max = 20)
    private List<String> imgUrls;

    private BigDecimal price;

    private Integer stock;

    private List<UUID> removeSpecIds;

    @Valid
    private List<NewSpecDto> newSpecs;

    @Data
    public static class NewSpecDto {
        @Length(max = 20)
        private String key;

        @Length(max = 200)
        private String value;
    }
}
