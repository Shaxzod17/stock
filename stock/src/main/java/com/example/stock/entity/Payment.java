package com.example.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter @Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "debt_id")
    @JsonIgnore 
    private Debt debt;
}