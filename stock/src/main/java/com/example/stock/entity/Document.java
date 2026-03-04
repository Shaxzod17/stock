package com.example.stock.entity;
import jakarta.persistence.*;  
import lombok.*; 
import java.time.LocalDate;

@Entity
@Table(name = "documents")
@Getter @Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @ManyToOne
    private Stock stock;
}
