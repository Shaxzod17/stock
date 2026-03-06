package com.example.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.stock.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}