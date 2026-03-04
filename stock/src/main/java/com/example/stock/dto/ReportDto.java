package com.example.stock.dto; 
import lombok.*; 

@AllArgsConstructor
@Getter
public class ReportDto {

    private String name;
    private String code;
    private Double meterKvadrat;
    private Double increased;
    private Double decreased;
}
