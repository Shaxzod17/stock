package com.example.stock.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MagazineChiqimTarixResponse {
    private Long id;
    private String productCode;
    private String productName;
    private Double amount;
    private LocalDateTime createdAt;
}