package com.example.server.dto.spec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseSpecDto {
    private UUID id;

    private String key;

    private String value;

    private Date createdAt;

    private Date updatedAt;

}
