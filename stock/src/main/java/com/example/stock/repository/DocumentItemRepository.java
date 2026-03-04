package com.example.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.entity.DocumentItem;

public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long> {}