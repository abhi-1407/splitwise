package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.model.Settlement;
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
    BalanceController(BalanceService balanceService, DebtSimplificationService debtSimplificationService){
        this.balanceService = balanceService;
        this.debtSimplificationService = debtSimplificationService;
    }
    @GetMapping("")
    public Map<String, Map<String, Long>> getAllBalance(){
        return balanceService.getAllBalances();
    }

    @GetMapping("/user/{userId}")
    public Map<String, Long> getBalance(@PathVariable String userId){
        return balanceService.getUserBalances(userId);
    }

    @GetMapping("/simplify")
    public List<Settlement> simplifyDebts(){
        return debtSimplificationService.getSimplifiedBalance();
    }
}
