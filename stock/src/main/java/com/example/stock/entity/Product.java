package com.example.stock.entity;
import jakarta.persistence.*;  
import lombok.*; 

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private Double meterKvadrat;
}
