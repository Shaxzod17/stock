package com.example.stock.dto;

import lombok.Data;

@Data
public class KirimRequest {
    private String code;
    private Double quantity;
}