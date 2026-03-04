package com.example.stock.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.ReportDto;
import com.example.stock.service.ReportService;

import lombok.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping
    public List<ReportDto> report(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return service.getReport(start, end);
    }
}
