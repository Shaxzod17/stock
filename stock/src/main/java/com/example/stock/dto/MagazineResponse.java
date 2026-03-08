package com.example.stock.dto;

import lombok.Data;

@Data
public class MagazineResponse {
    private Long id;
    private String code;
    private String name;
    private Double meterKvadrat;
    private Double quantity;
}