package com.josepdevs.Infra.output;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdevs.Domain.dto.Users;

public interface UserJpaRepository extends JpaRepository<Users, UUID> {
//sin implementación, solo para poder llamar a los métodos de JPARepo
}
