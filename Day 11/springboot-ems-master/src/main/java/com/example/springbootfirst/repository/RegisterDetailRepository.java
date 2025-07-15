package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterDetailRepository extends JpaRepository<RegisterDetails,Integer> {

    RegisterDetails findByEmail(String email);

    Optional<RegisterDetails> findByUserName(String username);

    List<RegisterDetails> findByRoles(Roles role);
}
