package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.model.SimplifiedSettlement;
import com.abhilash.splitwise.service.BalanceService;
import com.abhilash.splitwise.service.DebtSimplificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceController {
    private final BalanceService balanceService;
    private final DebtSimplificationService debtSimplificationService;
    public BalanceController(BalanceService balanceService, DebtSimplificationService debtSimplificationService) {
        this.balanceService = balanceService;
        this.debtSimplificationService = debtSimplificationService;
    }
    @GetMapping
    public ResponseEntity<Map<String, Map<String, Long>>> getAllBalances() {
        return ResponseEntity.ok(balanceService.getAllBalances());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Long>> getUserBalances(@PathVariable String userId) {
        return ResponseEntity.ok(balanceService.getUserBalances(userId));
    }
    @GetMapping("/simplify")
    public ResponseEntity<List<SimplifiedSettlement>> getSimplifiedDebts() {
        return ResponseEntity.ok(debtSimplificationService.getSimplifiedBalance());
    }
}
