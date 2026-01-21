package com.win777.controller;

import com.win777.dto.BalanceResponseDTO;
import com.win777.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
@Tag(name = "Balance Management", description = "APIs for viewing user balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user balance", description = "Retrieve the current balance for a user")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long userId) {
        BalanceResponseDTO balance = balanceService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }
}
