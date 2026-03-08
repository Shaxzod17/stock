package com.example.stock.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "magazine_chiqim_tarix")
@Getter @Setter
public class MagazineChiqimTarix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private String productName;
    private Double amount;
    private LocalDateTime createdAt;
}