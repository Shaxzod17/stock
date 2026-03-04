package com.example.stock.entity;

import jakarta.persistence.*;  
import lombok.*; 

@Entity
@Table(name = "stocks")
@Getter @Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}