package com.josepedevs.infra.persistence.jpa.repository;

import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPersonRepository extends JpaRepository<PersonDataDao, String> {}
