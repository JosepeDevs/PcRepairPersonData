package com.josepedevs.infra.persistence;

import com.josepedevs.infra.persistence.dto.PersonData;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonData, UUID> {}
