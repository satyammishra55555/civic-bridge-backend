package com.civicbridge.repository;

import com.civicbridge.entity.Role;
import com.civicbridge.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}