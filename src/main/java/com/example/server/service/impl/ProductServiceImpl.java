package com.example.server.service.impl;

import com.example.server.dto.item.CreateItemDto;
import com.example.server.dto.item.ResponseItemDto;
import com.example.server.dto.item.UpdateItemDto;
import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.dto.product.UpdateProductGeneralDto;
import com.example.server.entity.Item;
import com.example.server.entity.Product;
import com.example.server.entity.Spec;
import com.example.server.exception.CustomException;
import com.example.server.mapper.ItemMapper;
import com.example.server.mapper.ProductMapper;
import com.example.server.repository.ItemRepository;
import com.example.server.repository.ProductRepository;
import com.example.server.repository.SpecRepository;
import com.example.server.service.ProductService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final SpecRepository specRepository;
    private final ProductMapper productMapper;
    private final ItemMapper itemMapper;

    @Override
    public ResponseProductDto createProduct(CreateProductDto createProductDto) {
        Product savedProduct = productRepository
                .save(productMapper.fromCreateDto(createProductDto));
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ResponseProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public ResponseProductDto getProductByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Product not found."));
        return productMapper.toResponseDto(product);
    }

    @Override
    public ResponseProductDto getProductBySearchName(String searchName) {
        Product product = productRepository.findBySearchName(searchName)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Product not found."));
        return productMapper.toResponseDto(product);
    }

    @Override
    public ResponseProductDto updateProductGeneralByCode(String code, UpdateProductGeneralDto dto) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Product not found."));

        boolean isChanged = false;
        if (dto.getName() != null) {
            product.setName(dto.getName());
            isChanged = true;
        }
        if (dto.getBrand() != null) {
            product.setBrand(dto.getBrand());
            isChanged = true;
        }
        if (dto.getCategory() != null) {
            product.setCategory(dto.getCategory());
            isChanged = true;
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
            isChanged = true;
        }
        if (dto.getImgUrls() != null) {
            product.setImgUrls(dto.getImgUrls());
            isChanged = true;
        }
        if (dto.getIsPublished() != null) {
            product.setPublished(dto.getIsPublished());
            isChanged = true;
        }

        if(isChanged) {
            product.setUpdatedAt(new Date());
            Product savedProduct = productRepository.save(product);
            return productMapper.toResponseDto(savedProduct);
        }
        return productMapper.toResponseDto(product);
    }

    @Override
    public ResponseItemDto createItem(CreateItemDto createItemDto) {
        Item newItem = itemMapper.fromCreateDto(createItemDto);

        return itemMapper.toResponseDto(itemRepository.save(newItem));
    }

    @Override
    public List<ResponseItemDto> getItems() {
        return itemRepository.findAll().stream().map(itemMapper::toResponseDto).toList();
    }

    @Override
    public ResponseItemDto updateItemBySku(String sku, UpdateItemDto dto) {
        Item item = itemRepository.findBySku(sku)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Item not found."));

        boolean isChanged = false;
        if (dto.getVariant() != null) {
            item.setVariant(dto.getVariant());
            isChanged = true;
        }
        if (dto.getImgUrls() != null) {
            item.setImgUrls(dto.getImgUrls());
            isChanged = true;
        }
        if (dto.getPrice() != null) {
            item.setPrice(dto.getPrice());
            isChanged = true;
        }
        if (dto.getStock() != null) {
            item.setStock(dto.getStock());
            isChanged = true;
        }

        if(dto.getRemoveSpecIds() != null && !dto.getRemoveSpecIds().isEmpty()) {
            isChanged = true;
            item.getSpecs().removeIf(spec -> dto.getRemoveSpecIds().contains(spec.getId()));
            specRepository.deleteAllById(dto.getRemoveSpecIds());
        }

        if(dto.getNewSpecs() != null && !dto.getNewSpecs().isEmpty()) {
            isChanged = true;
            dto.getNewSpecs().forEach(newSpecDto -> {
                Spec newSpec = Spec.builder()
                        .id(UUID.randomUUID())
                        .key(newSpecDto.getKey())
                        .value(newSpecDto.getValue())
                        .item(item)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build();
                specRepository.save(newSpec);
                item.getSpecs().add(newSpec);
            });
        }

        if(isChanged) {
            item.setUpdatedAt(new Date());
            return itemMapper.toResponseDto(itemRepository.save(item));
        }
        return itemMapper.toResponseDto(item);
    }
}
