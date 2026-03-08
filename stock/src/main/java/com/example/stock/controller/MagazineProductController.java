package com.example.stock.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.stock.dto.ChiqimRequest;
import com.example.stock.dto.MagazineRequest;
import com.example.stock.dto.MagazineResponse;
import com.example.stock.entity.MagazineChiqimTarix;
import com.example.stock.entity.MagazineProduct;
import com.example.stock.entity.MagazineTavarQuantity;
import com.example.stock.repository.MagazineChiqimTarixRepository;
import com.example.stock.repository.MagazineProductRepository;
import com.example.stock.repository.MagazineTavarQuantityRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/magazine/products")
@RequiredArgsConstructor
@CrossOrigin
public class MagazineProductController {

    private final MagazineProductRepository productRepository;
    private final MagazineTavarQuantityRepository tavarQuantityRepository;
    private final MagazineChiqimTarixRepository chiqimTarixRepository;

    @GetMapping
    public List<MagazineResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    MagazineResponse dto = new MagazineResponse();
                    dto.setId(product.getId());
                    dto.setCode(product.getCode());
                    dto.setName(product.getName());
                    dto.setMeterKvadrat(product.getMeterKvadrat());

                    Double quantity = tavarQuantityRepository
                            .findByProductId(product.getId())
                            .map(MagazineTavarQuantity::getQuantity)
                            .orElse(0.0);

                    dto.setQuantity(quantity);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<MagazineResponse> addProduct(@RequestBody MagazineRequest request) {

        Optional<MagazineProduct> existing = productRepository.findByCode(request.getCode());
        if (existing.isPresent()) {
            MagazineProduct product = existing.get();
            MagazineTavarQuantity qty = tavarQuantityRepository
                    .findByProductId(product.getId())
                    .orElse(new MagazineTavarQuantity());

            qty.setProduct(product);
            qty.setQuantity((qty.getQuantity() == null ? 0.0 : qty.getQuantity()) + request.getQuantity());
            tavarQuantityRepository.save(qty);

            MagazineResponse dto = new MagazineResponse();
            dto.setId(product.getId());
            dto.setCode(product.getCode());
            dto.setName(product.getName());
            dto.setMeterKvadrat(product.getMeterKvadrat());
            dto.setQuantity(qty.getQuantity());
            return ResponseEntity.ok(dto);
        }

        MagazineProduct product = new MagazineProduct();
        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setMeterKvadrat(request.getMeterKvadrat());
        MagazineProduct saved = productRepository.save(product);

        MagazineTavarQuantity tavarQty = new MagazineTavarQuantity();
        tavarQty.setProduct(saved);
        tavarQty.setQuantity(request.getQuantity());
        tavarQuantityRepository.save(tavarQty);

        MagazineResponse dto = new MagazineResponse();
        dto.setId(saved.getId());
        dto.setCode(saved.getCode());
        dto.setName(saved.getName());
        dto.setMeterKvadrat(saved.getMeterKvadrat());
        dto.setQuantity(request.getQuantity());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/chiqim")
    public ResponseEntity<?> chiqim(@RequestBody ChiqimRequest request) {
        Optional<MagazineProduct> productOpt = productRepository.findByCode(request.getCode());

        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Bunday kodli tavar yo'q");
        }

        MagazineProduct product = productOpt.get();

        MagazineTavarQuantity qty = tavarQuantityRepository
                .findByProductId(product.getId())
                .orElseThrow(() -> new RuntimeException("Qoldiq topilmadi"));

        if (qty.getQuantity() < request.getAmount()) {
            return ResponseEntity.badRequest().body("Yetarli miqdor yo'q");
        }

        qty.setQuantity(qty.getQuantity() - request.getAmount());
        tavarQuantityRepository.save(qty);

        // Tarixga yoz
        MagazineChiqimTarix tarix = new MagazineChiqimTarix();
        tarix.setProductCode(product.getCode());
        tarix.setProductName(product.getName());
        tarix.setAmount(request.getAmount());
        tarix.setCreatedAt(java.time.LocalDateTime.now());
        chiqimTarixRepository.save(tarix);

        MagazineResponse dto = new MagazineResponse();
        dto.setId(product.getId());
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setMeterKvadrat(product.getMeterKvadrat());
        dto.setQuantity(qty.getQuantity());

        return ResponseEntity.ok(dto);
    }
}