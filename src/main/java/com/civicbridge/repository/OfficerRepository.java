package com.civicbridge.repository;

import com.civicbridge.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

    Optional<Officer> findByUserId(Long userId);

}