package com.example.stock.repository;

import com.example.stock.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {}