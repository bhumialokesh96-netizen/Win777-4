package com.win777.service;

import com.win777.dto.BalanceResponseDTO;
import com.win777.entity.UserEntity;
import com.win777.exception.ResourceNotFoundException;
import com.win777.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    private UserRepository userRepository;

    public BalanceResponseDTO getBalance(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        return new BalanceResponseDTO(user.getId(), user.getUsername(), user.getBalance());
    }
}
