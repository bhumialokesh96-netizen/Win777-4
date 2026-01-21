package com.win777.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Double balance;
    private String referralCode;
}
