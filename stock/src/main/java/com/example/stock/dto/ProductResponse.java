package com.example.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private Double meterKvadrat;
    private Double quantity;
}