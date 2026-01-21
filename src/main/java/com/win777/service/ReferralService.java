package com.win777.service;

import com.win777.dto.ReferralTreeDTO;
import com.win777.entity.ReferralEntity;
import com.win777.entity.UserEntity;
import com.win777.exception.ResourceNotFoundException;
import com.win777.repository.ReferralRepository;
import com.win777.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReferralService {

    private final ReferralRepository referralRepository;
    private final UserRepository userRepository;

    public ReferralService(ReferralRepository referralRepository, UserRepository userRepository) {
        this.referralRepository = referralRepository;
        this.userRepository = userRepository;
    }

    public ReferralTreeDTO getReferralTree(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        // TODO: Add depth limit and batch loading for deep referral chains
        return buildReferralTree(user);
    }

    private ReferralTreeDTO buildReferralTree(UserEntity user) {
        ReferralTreeDTO tree = new ReferralTreeDTO();
        tree.setUserId(user.getId());
        tree.setUsername(user.getUsername());

        List<ReferralEntity> referrals = referralRepository.findByReferrerId(user.getId());
        List<ReferralTreeDTO> children = new ArrayList<>();

        for (ReferralEntity referral : referrals) {
            userRepository.findById(referral.getReferredId()).ifPresent(referredUser -> {
                children.add(buildReferralTree(referredUser));
            });
        }

        tree.setReferrals(children);
        return tree;
    }
}
