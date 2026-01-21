package com.win777.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDTO {
    private Long userId;
    private String username;
    private Double balance;
}
