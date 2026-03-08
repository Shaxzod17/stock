package com.example.stock.dto;

import lombok.Data;

@Data
public class ChiqimRequest {
    private String code;
    private Double amount;
}