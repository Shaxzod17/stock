package com.example.stock.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.stock.entity.DocumentItem;
import com.example.stock.entity.Product;

public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long> {
    List<DocumentItem> findByProduct(Product product);
    List<DocumentItem> findByDocumentId(Long documentId);
    void deleteByProduct(Product product);
}