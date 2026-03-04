package com.example.stock.entity;
import jakarta.persistence.*;  
import lombok.*; 

@Entity
@Table(name = "document_items")
@Getter @Setter
public class DocumentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Document document;

    @ManyToOne
    private Product product;

    private Double quantity;
}