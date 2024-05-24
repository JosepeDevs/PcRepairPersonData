package com.josepdevs.Infra.output;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdevs.Domain.dto.PersonData;

public interface PersonJpaRepository extends JpaRepository<PersonData, UUID> {
//sin implementación, solo para poder llamar a los métodos de JPARepo
}
