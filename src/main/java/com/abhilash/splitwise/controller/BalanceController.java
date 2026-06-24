package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.SettleBalanceRequest;
import com.abhilash.splitwise.service.BalanceService;
import com.abhilash.splitwise.service.DebtSimplificationService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
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
    public void getAllBalance(){
        balanceService.getAllBalances();
    }

    @GetMapping("/{userId}")
    public Map<String, Long> getBalance(@PathVariable String userId){
        return balanceService.getUserBalances(userId);
    }

    @PostMapping("/settle")
    public String settle(SettleBalanceRequest request){
        return balanceService.settleBalance(request.getDebtorId(), request.getCreditorId(), request.getAmount());
    }

    @GetMapping("/simplify")
    public void simplifyDebts(){
        return debtSimplificationService.getSimplifiedBalance();
    }
}
