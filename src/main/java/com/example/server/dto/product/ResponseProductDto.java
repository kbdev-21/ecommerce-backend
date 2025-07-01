package com.example.server.dto.product;

import com.example.server.dto.item.ResponseItemDto;
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

    private List<ResponseItemDto> items;

    private List<ResponseProductDtoRating> ratings;

    private Date createdAt;

    private Date updatedAt;

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ResponseProductDtoRating {
        private UUID id;

        private double score;

        private String comment;

        private List<String> imgUrls;

        private Date createdAt;

        private Date updatedAt;
    }
}
