package com.example.stock.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.MagazineChiqimTarixResponse;
import com.example.stock.repository.MagazineChiqimTarixRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/magazine/hisobot")
@RequiredArgsConstructor
@CrossOrigin
public class MagazineHisobotController {

    private final MagazineChiqimTarixRepository chiqimTarixRepository;

    @GetMapping
    public List<MagazineChiqimTarixResponse> getAll() {
        return chiqimTarixRepository.findAll()  // tarixRepository emas!
                .stream()
                .map(t -> {
                    MagazineChiqimTarixResponse dto = new MagazineChiqimTarixResponse();
                    dto.setId(t.getId());
                    dto.setProductCode(t.getProductCode());
                    dto.setProductName(t.getProductName());
                    dto.setAmount(t.getAmount());
                    dto.setCreatedAt(t.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        chiqimTarixRepository.deleteAll();  // tarixRepository emas!
        return ResponseEntity.ok("Barcha tarix o'chirildi");
    }
}