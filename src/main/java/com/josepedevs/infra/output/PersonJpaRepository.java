package com.josepedevs.infra.output;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepedevs.domain.entities.PersonData;

public interface PersonJpaRepository extends JpaRepository<PersonData, UUID> {
//sin implementación, solo para poder llamar a los métodos de JPARepo
}
