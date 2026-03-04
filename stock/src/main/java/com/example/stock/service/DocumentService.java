package com.example.stock.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.stock.dto.DocumentItemRequest;
import com.example.stock.dto.DocumentRequest;
import com.example.stock.entity.Document;
import com.example.stock.entity.DocumentItem;
import com.example.stock.entity.DocumentType;
import com.example.stock.entity.Product;
import com.example.stock.entity.Stock;
import com.example.stock.entity.StockQuantity;
import com.example.stock.repository.DocumentItemRepository;
import com.example.stock.repository.DocumentRepository;
import com.example.stock.repository.ProductRepository;
import com.example.stock.repository.StockQuantityRepository;
import com.example.stock.repository.StockRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockQuantityRepository stockQuantityRepository;

    @Transactional
    public void create(DocumentRequest request) {

        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Document document = new Document();
        document.setDate(LocalDate.now());
        document.setType(request.getType());
        document.setStock(stock);

        documentRepository.save(document);

        for (DocumentItemRequest itemReq : request.getItems()) {

            Product product = productRepository
                    .findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));

            DocumentItem item = new DocumentItem();
            item.setDocument(document);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            itemRepository.save(item);

            StockQuantity stockQuantity =
                    stockQuantityRepository
                            .findByStockAndProduct(stock, product)
                            .orElseGet(() -> {
                                StockQuantity sq = new StockQuantity();
                                sq.setStock(stock);
                                sq.setProduct(product);
                                sq.setQuantity(0.0);
                                return sq;
                            });

            if (request.getType() == DocumentType.IN) {
                stockQuantity.setQuantity(
                        stockQuantity.getQuantity() + itemReq.getQuantity());
            } else if (request.getType() == DocumentType.OUT) {
                if (stockQuantity.getQuantity() < itemReq.getQuantity()) {
                    throw new RuntimeException(
                        "Not enough stock for product ID: " + itemReq.getProductId() +
                        ". Available: " + stockQuantity.getQuantity() +
                        ", Requested: " + itemReq.getQuantity()
                    );
                }
                stockQuantity.setQuantity(
                        stockQuantity.getQuantity() - itemReq.getQuantity());
            } else {
                throw new RuntimeException("Unknown document type: " + request.getType());
            }

            stockQuantityRepository.save(stockQuantity);
        }
    }
}