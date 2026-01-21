package com.win777.controller;

import com.win777.dto.TransactionDTO;
import com.win777.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction Management", description = "APIs for viewing transaction history")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user transactions", description = "Retrieve paginated transaction history for a user")
    public ResponseEntity<Page<TransactionDTO>> getTransactions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TransactionDTO> transactions = transactionService.getTransactions(userId, page, size);
        return ResponseEntity.ok(transactions);
    }
}
