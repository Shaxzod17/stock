package com.example.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.entity.MagazineQuantity;

public interface MagazineQuantityRepository extends JpaRepository<MagazineQuantity, Long> {

    Optional<MagazineQuantity> findByProductId(Long productId);

}