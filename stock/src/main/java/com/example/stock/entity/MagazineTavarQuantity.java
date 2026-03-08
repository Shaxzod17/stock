package com.example.stock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "magazine_tavar_quantity")
@Getter @Setter
public class MagazineTavarQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "magazine_product_id")
    private MagazineProduct product;

    private Double quantity = 0.0;
}