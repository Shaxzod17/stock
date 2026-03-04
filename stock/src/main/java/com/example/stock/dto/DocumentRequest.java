package com.example.stock.dto;
import lombok.*; 
import java.util.List;

import com.example.stock.entity.DocumentType;


@Getter @Setter
public class DocumentRequest {
    private Long stockId;
    private DocumentType type;
    private List<DocumentItemRequest> items;
}