package com.josepdevs.Infra.output;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdevs.Domain.dto.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
//sin implementación, solo para poder llamar a los métodos de JPARepo
}
