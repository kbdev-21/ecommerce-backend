package com.example.server.controller;

import com.example.server.common.record.ApiResponse;
import com.example.server.dto.item.CreateItemDto;
import com.example.server.dto.item.ResponseItemDto;
import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.dto.product.UpdateProductGeneralDto;
import com.example.server.entity.Product;
import com.example.server.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/products")
    public ApiResponse<ResponseProductDto> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Product created successfully.",
                productService.createProduct(createProductDto));
    }

    @GetMapping("/api/v1/products")
    public ApiResponse<List<ResponseProductDto>> getProducts() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Products retrieved successfully.",
                productService.getProducts());
    }

    @GetMapping("/api/v1/products/by-code/{code}")
    public ApiResponse<ResponseProductDto> getProductByCode(@PathVariable String code) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product retrieved successfully.",
                productService.getProductByCode(code));
    }

    @GetMapping("/api/v1/products/by-searchname/{searchName}")
    public ApiResponse<ResponseProductDto> getProductBySearchName(@PathVariable String searchName) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product retrieved successfully.",
                productService.getProductBySearchName(searchName));
    }

    @PatchMapping("/api/v1/products/general/by-code/{code}")
    public ApiResponse<ResponseProductDto> updateProductGeneralByCode(
            @PathVariable String code,
            @Valid @RequestBody UpdateProductGeneralDto updateProductGeneralDto) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product updated successfully.",
                productService.updateProductGeneralByCode(code, updateProductGeneralDto)
        );
    }

    @PostMapping("/api/v1/products/items")
    public ApiResponse<ResponseItemDto> createItem(@Valid @RequestBody CreateItemDto createItemDto) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Item created successfully.",
                productService.createItem(createItemDto)
        );
    }

    @GetMapping("/api/v1/products/items")
    public ApiResponse<List<ResponseItemDto>> getItems() {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Items retrieved successfully.",
                productService.getItems()
        );
    }
}
