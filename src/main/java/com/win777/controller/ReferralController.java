package com.win777.controller;

import com.win777.dto.ReferralTreeDTO;
import com.win777.service.ReferralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/referrals")
@Tag(name = "Referral Management", description = "APIs for viewing referral tree")
public class ReferralController {

    private final ReferralService referralService;

    public ReferralController(ReferralService referralService) {
        this.referralService = referralService;
    }

    @GetMapping("/tree/{userId}")
    @Operation(summary = "Get referral tree", description = "Retrieve the referral tree for a user")
    public ResponseEntity<ReferralTreeDTO> getReferralTree(@PathVariable Long userId) {
        ReferralTreeDTO tree = referralService.getReferralTree(userId);
        return ResponseEntity.ok(tree);
    }
}
