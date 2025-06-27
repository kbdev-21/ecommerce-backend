package com.example.server.service;

import com.example.server.dto.product.CreateProductDto;
import com.example.server.dto.product.ResponseProductDto;
import com.example.server.dto.product.UpdateProductGeneralDto;

import java.util.List;

public interface ProductService {
    ResponseProductDto createProduct(CreateProductDto createProductDto);

    List<ResponseProductDto> getProducts();

    ResponseProductDto getProductByCode(String code);

    ResponseProductDto getProductBySearchName(String searchName);

    ResponseProductDto updateProductGeneralByCode(String code, UpdateProductGeneralDto updateProductGeneralDto);
}
