package org.example.Employeespringboot.repository;

import org.example.Employeespringboot.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterDetails,Integer> {

    Optional<RegisterDetails> findByUserName(String username);
}
