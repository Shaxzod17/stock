package com.example.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.stock.entity.MagazineProduct;

public interface MagazineProductRepository extends JpaRepository<MagazineProduct, Long> {
    Optional<MagazineProduct> findByCode(String code);
}