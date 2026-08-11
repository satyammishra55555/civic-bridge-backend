package com.civicbridge.config;

import com.civicbridge.entity.Role;
import com.civicbridge.enums.RoleType;
import com.civicbridge.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole(RoleType.ADMIN);

        createRole(RoleType.OFFICER);

        createRole(RoleType.CITIZEN);

    }

    private void createRole(RoleType roleType) {

        if (roleRepository.findByName(roleType).isEmpty()) {

            Role role = Role.builder()
                    .name(roleType)
                    .description(roleType.name())
                    .build();

            roleRepository.save(role);

        }

    }

}