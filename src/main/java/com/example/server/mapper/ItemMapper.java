package com.example.server.mapper;

import com.example.server.dto.item.CreateItemDto;
import com.example.server.entity.Item;
import com.example.server.entity.Product;
import com.example.server.entity.Spec;
import com.example.server.exception.CustomException;
import com.example.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public Item fromCreateDto(CreateItemDto createItemDto) {
        Date currentDate = new Date();

        Product product = productRepository.findByCode(createItemDto.getProductCode())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Product not found."));

        List<Spec> specs = createItemDto.getSpecs().stream()
                .map(specDto -> {
                    return Spec.builder()
                            .id(UUID.randomUUID())
                            .key(specDto.getKey())
                            .value(specDto.getValue())
                            .createdAt(currentDate)
                            .updatedAt(currentDate)
                            .build();
                }).toList();

        Item item = modelMapper.map(createItemDto, Item.class);
        item.setProduct(product);
        item.setId(UUID.randomUUID());
        item.setSold(0);
        item.setCreatedAt(currentDate);
        item.setUpdatedAt(currentDate);
        item.setSpecs(specs);

        return item;
    }
}
