package com.example.stock.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.stock.dto.ReportDto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final EntityManager entityManager;

    public List<ReportDto> getReport(LocalDate start, LocalDate end) {

        String jpql = """
            SELECT new com.example.stock.dto.ReportDto(
                p.name,
                p.code,
                p.meterKvadrat,
                SUM(CASE WHEN d.type = com.example.stock.entity.DocumentType.IN THEN di.quantity ELSE 0 END),
                SUM(CASE WHEN d.type = com.example.stock.entity.DocumentType.OUT THEN di.quantity ELSE 0 END)
            )
            FROM DocumentItem di
            JOIN di.document d
            JOIN di.product p
            WHERE d.date BETWEEN :start AND :end
            GROUP BY p.id, p.name, p.code, p.meterKvadrat
        """;

        return entityManager.createQuery(jpql, ReportDto.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
}
