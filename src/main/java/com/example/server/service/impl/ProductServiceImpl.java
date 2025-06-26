package com.example.server.service.impl;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.entity.Item;
import com.example.server.entity.Product;
import com.example.server.entity.Spec;
import com.example.server.helper.StringHelper;
import com.example.server.mapper.ProductMapper;
import com.example.server.repository.ProductRepository;
import com.example.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ResponseProductDto createProduct(CreateProductDto createProductDto) {
        Product newProduct = productMapper.fromCreateDto(createProductDto);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ResponseProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> productMapper.toResponseDto(product))
                .toList();
    }

    @Override
    public ResponseProductDto getProductByCode(String code) {
        Product product = productRepository.findByCode(code).orElseThrow();
        return productMapper.toResponseDto(product);
    }
}
