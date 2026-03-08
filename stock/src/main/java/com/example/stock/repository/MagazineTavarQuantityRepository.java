package com.example.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.stock.entity.MagazineTavarQuantity;

public interface MagazineTavarQuantityRepository extends JpaRepository<MagazineTavarQuantity, Long> {
    Optional<MagazineTavarQuantity> findByProductId(Long productId);
}