package com.win777.service;

import com.win777.dto.*;
import com.win777.entity.ReferralEntity;
import com.win777.entity.UserEntity;
import com.win777.exception.AuthenticationException;
import com.win777.exception.DuplicateResourceException;
import com.win777.exception.ResourceNotFoundException;
import com.win777.repository.ReferralRepository;
import com.win777.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReferralRepository referralRepository;

    public UserService(UserRepository userRepository, ReferralRepository referralRepository) {
        this.userRepository = userRepository;
        this.referralRepository = referralRepository;
    }

    @Transactional
    public UserDTO register(RegistrationRequestDTO request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        // TODO: In production, hash password using BCryptPasswordEncoder before storing
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setBalance(0.0);
        user.setReferralCode(UUID.randomUUID().toString().substring(0, 8));

        // Handle referral code if provided
        if (request.getReferralCode() != null && !request.getReferralCode().isEmpty()) {
            UserEntity referrer = userRepository.findByReferralCode(request.getReferralCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid referral code"));
            user.setReferredBy(referrer.getId());
            
            // Save user first to get the ID
            UserEntity savedUser = userRepository.save(user);
            
            // Create referral entry
            ReferralEntity referral = new ReferralEntity();
            referral.setReferrerId(referrer.getId());
            referral.setReferredId(savedUser.getId());
            referralRepository.save(referral);
            
            return convertToDTO(savedUser);
        }

        UserEntity savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO login(LoginRequestDTO request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthenticationException("Invalid username or password"));

        // TODO: In production, use BCryptPasswordEncoder.matches() for secure password comparison
        if (!user.getPassword().equals(request.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        return convertToDTO(user);
    }

    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(UserEntity user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBalance(user.getBalance());
        dto.setReferralCode(user.getReferralCode());
        return dto;
    }
}
