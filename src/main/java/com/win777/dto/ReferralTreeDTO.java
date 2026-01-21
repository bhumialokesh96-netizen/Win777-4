package com.win777.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferralTreeDTO {
    private Long userId;
    private String username;
    private List<ReferralTreeDTO> referrals;
}
