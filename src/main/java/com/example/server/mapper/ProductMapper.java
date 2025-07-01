package com.example.server.mapper;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.entity.Item;
import com.example.server.entity.Product;
import com.example.server.entity.Spec;
import com.example.server.common.helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ResponseProductDto toResponseDto(Product product) {
        return modelMapper.map(product, ResponseProductDto.class);
    }

    public Product fromCreateDto(CreateProductDto reqDto) {
        Date currentDate = new Date();
        String searchName = StringHelper.normalizedVietnameseText(reqDto.getName());

        List<Item> items = reqDto.getItems().stream().map(itemDto -> {
            Item item = Item.builder()
                    .id(UUID.randomUUID())
                    .sku(itemDto.getSku())
                    .variant(itemDto.getVariant())
                    .imgUrls(itemDto.getImgUrls())
                    .price(itemDto.getPrice())
                    .stock(itemDto.getStock())
                    .sold(0)
                    .createdAt(currentDate)
                    .updatedAt(currentDate)
                    .build();

            List<Spec> specs = itemDto.getSpecs().stream().map(specDto -> {
                return Spec.builder()
                        .id(UUID.randomUUID())
                        .key(specDto.getKey())
                        .value(specDto.getValue())
                        .item(item)
                        .createdAt(currentDate)
                        .updatedAt(currentDate)
                        .build();
            }).toList();
            item.setSpecs(specs);

            return item;
        }).toList();

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .code(reqDto.getCode())
                .name(reqDto.getName())
                .searchName(searchName)
                .brand(reqDto.getBrand())
                .category(reqDto.getCategory())
                .description(reqDto.getDescription())
                .imgUrls(reqDto.getImgUrls())
                .isPublished(reqDto.isPublished())
                .createdAt(currentDate)
                .updatedAt(currentDate)
                .items(items)
                .build();

        items.forEach(item -> item.setProduct(product));

        return product;
    }

}
