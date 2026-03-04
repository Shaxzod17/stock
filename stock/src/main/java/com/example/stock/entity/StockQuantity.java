package com.example.stock.entity;
import jakarta.persistence.*;  
import lombok.*; 

@Entity
@Table(name = "stock_quantities",
       uniqueConstraints = @UniqueConstraint(columnNames = {"stock_id","product_id"}))
@Getter @Setter
public class StockQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stock stock;

    @ManyToOne
    private Product product;

    private Double quantity = 0.0;
}
