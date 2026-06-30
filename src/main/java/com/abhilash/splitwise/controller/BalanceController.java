package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.model.SimplifiedSettlement;
import com.abhilash.splitwise.service.BalanceService;
import com.abhilash.splitwise.service.DebtSimplificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceController {
    private final BalanceService balanceService;
    private final DebtSimplificationService debtSimplificationService;
    public BalanceController(BalanceService balanceService, DebtSimplificationService debtSimplificationService){
        this.balanceService = balanceService;
        this.debtSimplificationService = debtSimplificationService;
    }
    @GetMapping()
    public Map<String, Map<String, Long>> getAllBalance(){
        return balanceService.getAllBalances();
    }
    @GetMapping("/user/{userId}")
    public Map<String, Long> getBalances(@PathVariable String userId){
        return balanceService.getUserBalances(userId);
    }
    @GetMapping("/simplify")
    public List<SimplifiedSettlement> getSimplifiedDebts(){
        return debtSimplificationService.getSimplifiedBalance();
    }
}
