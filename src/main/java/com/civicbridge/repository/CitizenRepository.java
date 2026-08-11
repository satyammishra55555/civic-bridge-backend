package com.civicbridge.repository;

import com.civicbridge.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByUserId(Long userId);

}