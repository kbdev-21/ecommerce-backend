package com.example.server.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "search_name", nullable = false)
    private String searchName;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "img_urls", nullable = false)
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<String> imgUrls;

    @Column(name = "is_published", nullable = false)
    private boolean isPublished;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Double getAvgRating() {
        if(ratings == null || ratings.isEmpty()) return null;

        Double totalRatingPoint = ratings.stream()
                .mapToDouble(rating -> rating.getScore())
                .sum();

        return totalRatingPoint / ratings.size();
    }

    public int getTotalStock() {
        if (items == null) return 0;
        return items.stream().mapToInt(Item::getStock).sum();
    }

    public int getTotalSold() {
        if (items == null) return 0;
        return items.stream().mapToInt(Item::getSold).sum();
    }
}