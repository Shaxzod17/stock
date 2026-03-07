package com.example.stock.controller;

import com.example.stock.entity.Debt;
import com.example.stock.entity.Payment;
import com.example.stock.repository.DebtRepository;
import com.example.stock.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/debts")
@RequiredArgsConstructor
@CrossOrigin
public class DebtController {

    private final DebtRepository debtRepository;
    private final PaymentRepository paymentRepository;

    @GetMapping
    public List<Debt> getAll() {
        return debtRepository.findAll();
    }


    @PostMapping
    public Debt create(@RequestBody Debt debt) {
        return debtRepository.save(debt);
    }

    @PostMapping("/{id}/payments")
    public Payment addPayment(@PathVariable Long id,
                              @RequestBody Map<String, Object> body) {
        Debt debt = debtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debt not found: " + id));

        Payment payment = new Payment();
        payment.setDebt(debt);
        payment.setAmount(Double.parseDouble(body.get("amount").toString()));
        payment.setDate(java.time.LocalDate.parse(body.get("date").toString()));

        return paymentRepository.save(payment);
    }
}