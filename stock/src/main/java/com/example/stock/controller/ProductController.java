package com.example.stock.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.ProductResponse;
import com.example.stock.entity.DocumentType;
import com.example.stock.entity.Product;
import com.example.stock.repository.DocumentItemRepository;
import com.example.stock.repository.ProductRepository;
import com.example.stock.repository.StockQuantityRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;
    private final DocumentItemRepository documentItemRepository;
    private final StockQuantityRepository stockQuantityRepository;

    @GetMapping
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(product -> {
            double totalQty = documentItemRepository
                    .findByProduct(product)
                    .stream()
                    .mapToDouble(item -> {
                        double qty = item.getQuantity() != null ? item.getQuantity() : 0.0;
                        return item.getDocument().getType() == DocumentType.IN ? qty : -qty;
                    })
                    .sum();

            ProductResponse dto = new ProductResponse();
            dto.setId(product.getId());
            dto.setCode(product.getCode());
            dto.setName(product.getName());
            dto.setMeterKvadrat(product.getMeterKvadrat());
            dto.setQuantity(totalQty);
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

   @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            stockQuantityRepository.deleteByProduct(product);   
            stockQuantityRepository.flush();

            documentItemRepository.deleteAll(documentItemRepository.findByProduct(product)); 
            documentItemRepository.flush();

            productRepository.delete(product);              
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}