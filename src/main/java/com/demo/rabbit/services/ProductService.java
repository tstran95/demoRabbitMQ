package com.demo.rabbit.services;

import com.demo.rabbit.dto.Product;

import java.util.List;

public interface ProductService {
    List<?> getAll();

    Product createProd(Product product);

    Product updateProd(Product product);

    void deleteProd(Long id);
}
