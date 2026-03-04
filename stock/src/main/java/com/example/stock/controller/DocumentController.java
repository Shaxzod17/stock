package com.example.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.DocumentRequest;
import com.example.stock.service.DocumentService;

import lombok.*;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentRequest request) {
        service.create(request);
        return ResponseEntity.ok("Document created");
    }
}
