package com.example.stock.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "magazine_product")
@Getter @Setter
public class MagazineProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private Double meterKvadrat;
}
