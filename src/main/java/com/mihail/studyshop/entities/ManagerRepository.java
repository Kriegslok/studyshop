package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    List<Manager> findByFirstName(String firstName);

    List<Manager> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByInn(String inn);

}
