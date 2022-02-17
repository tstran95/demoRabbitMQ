package com.demo.rabbit.services.impl;

import com.demo.rabbit.dto.Product;
import com.demo.rabbit.repository.ProductRepository;
import com.demo.rabbit.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<?> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createProd(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProd(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProd(Long id) {

    }
}
