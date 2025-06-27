package com.example.server.service.impl;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.dto.product.UpdateProductGeneralDto;
import com.example.server.entity.Product;
import com.example.server.exception.custom.NotFoundException;
import com.example.server.mapper.ProductMapper;
import com.example.server.repository.ProductRepository;
import com.example.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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

    @Override
    public ResponseProductDto getProductBySearchName(String searchName) {
        Product product = productRepository.findBySearchName(searchName)
                .orElseThrow(() -> new NotFoundException("Product not found."));
        return productMapper.toResponseDto(product);
    }

    @Override
    public ResponseProductDto updateProductGeneralByCode(String code, UpdateProductGeneralDto updateProductGeneralDto) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Product not found."));

        boolean isChanged = false;
        if (updateProductGeneralDto.getName() != null) {
            product.setName(updateProductGeneralDto.getName());
            isChanged = true;
        }
        if (updateProductGeneralDto.getBrand() != null) {
            product.setBrand(updateProductGeneralDto.getBrand());
            isChanged = true;
        }
        if (updateProductGeneralDto.getCategory() != null) {
            product.setCategory(updateProductGeneralDto.getCategory());
            isChanged = true;
        }
        if (updateProductGeneralDto.getDescription() != null) {
            product.setDescription(updateProductGeneralDto.getDescription());
            isChanged = true;
        }
        if (updateProductGeneralDto.getImgUrls() != null) {
            product.setImgUrls(updateProductGeneralDto.getImgUrls());
            isChanged = true;
        }
        if (updateProductGeneralDto.getIsPublished() != null) {
            product.setPublished(updateProductGeneralDto.getIsPublished());
            isChanged = true;
        }

        if(isChanged) {
            product.setUpdatedAt(new Date());
            Product savedProduct = productRepository.save(product);
            return productMapper.toResponseDto(savedProduct);
        }
        return productMapper.toResponseDto(product);
    }
}
