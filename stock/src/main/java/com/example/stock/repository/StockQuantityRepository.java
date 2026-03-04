package com.example.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.entity.Product;
import com.example.stock.entity.Stock;
import com.example.stock.entity.StockQuantity;

public interface StockQuantityRepository
        extends JpaRepository<StockQuantity, Long> {

    Optional<StockQuantity> findByStockAndProduct(Stock stock, Product product);

    List<StockQuantity> findByStockId(Long stockId);
}