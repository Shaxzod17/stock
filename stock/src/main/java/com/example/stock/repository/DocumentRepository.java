package com.example.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {}
