package com.example.server.controller;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.entity.Product;
import com.example.server.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/products")
    public ResponseProductDto createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto);
    }

    @GetMapping("/api/v1/products")
    public List<ResponseProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/api/v1/products/by-code/{code}")
    public ResponseProductDto getProductByCode(@PathVariable String code) {
        return productService.getProductByCode(code);
    }
}
