package com.demo.rabbit.repository;

import com.demo.rabbit.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
