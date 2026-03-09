package com.example.stock.dto;

import lombok.Data;

@Data
public class MagazineRequest {
    private String code;
    private String name;
    private Double meterKvadrat;
}