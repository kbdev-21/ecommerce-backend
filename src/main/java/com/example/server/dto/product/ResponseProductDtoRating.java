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
public class ResponseProductDtoRating {
    private UUID id;

    private double score;

    private String comment;

    private List<String> imgUrls;

    private Date createdAt;

    private Date updatedAt;
}
