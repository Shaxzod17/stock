package com.example.stock.controller;

import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    @GetMapping
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @RequestBody Stock body) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        stock.setName(body.getName());
        return stockRepository.save(stock);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockRepository.deleteById(id);
    }
}