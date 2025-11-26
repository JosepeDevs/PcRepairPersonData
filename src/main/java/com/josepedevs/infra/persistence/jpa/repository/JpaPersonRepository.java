package com.josepedevs.infra.persistence.jpa.repository;

import com.josepedevs.infra.persistence.jpa.dto.PersonDataDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPersonRepository extends JpaRepository<PersonDataDao, String> {

    List<PersonDataDao> findAllByDeleted(boolean isDeleted);

    Optional<PersonDataDao> findByIdUserAndDeleted(String idUser, boolean isDeleted);

    Optional<PersonDataDao> findByIdUser(String idUser);
}
