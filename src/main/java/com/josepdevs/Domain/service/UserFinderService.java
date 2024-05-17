package com.josepdevs.Domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.josepdevs.Domain.dto.UserEntity;
import com.josepdevs.Domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserFinderService {
    
	private final UserRepository userRepository;

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.searchUser(id);
    }

}
