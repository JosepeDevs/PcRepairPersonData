package com.josepdevs.Domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.Entities.Users;
import com.josepdevs.Domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserFinderService {
    
	private final UserRepository userRepository;

    public Optional<Users> findById(UUID id) {
        return userRepository.searchUser(id);
    }
    
    public Optional<Users> findByEmail(String email) {
        return userRepository.searchUserByEmail(email);
    }

}
