package com.example.stock.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "debts")
@Getter @Setter
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    // which stock gave the debt (lender)
    private Long fromStockId;

    // which stock received the debt (borrower)
    private Long toStockId;

    private Double amount;

    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments = new ArrayList<>();
}