package com.example.stock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.DocumentRequest;
import com.example.stock.entity.Document;
import com.example.stock.entity.DocumentItem;
import com.example.stock.service.DocumentService;

import lombok.*;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @GetMapping
        public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}/items")
        public ResponseEntity<List<DocumentItem>> getItems(@PathVariable Long id) {
        return ResponseEntity.ok(service.getItems(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentRequest request) {
        service.create(request);
        return ResponseEntity.ok("Document created");
    }
}
