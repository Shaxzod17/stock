package com.example.stock.dto; 
import lombok.*; 

@Getter @Setter
public class DocumentItemRequest {
    private Long productId;
    private Double quantity;
}
