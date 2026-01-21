package com.win777.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private Long userId;
    private String type;
    private Double amount;
    private String status;
    private String description;
    private LocalDateTime createdAt;
}
