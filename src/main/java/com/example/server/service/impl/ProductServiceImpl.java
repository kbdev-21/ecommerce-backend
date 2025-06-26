package com.example.server.service.impl;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.entity.Product;
import com.example.server.exception.custom.NotFoundException;
import com.example.server.mapper.ProductMapper;
import com.example.server.repository.ProductRepository;
import com.example.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Product not found."));
        return productMapper.toResponseDto(product);
    }
}
