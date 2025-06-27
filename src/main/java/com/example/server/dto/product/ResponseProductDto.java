package com.example.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseProductDto {
    private UUID id;

    private String code;

    private String name;

    private String searchName;

    private String brand;

    private String category;

    private String description;

    private Double avgRating; //unmapped

    private int totalStock; //unmapped

    private int totalSold; //unmapped

    private List<String> imgUrls;

    private List<ResponseProductDtoItem> items;

    private List<ResponseProductDtoRating> ratings;

    private Date createdAt;

    private Date updatedAt;
}
