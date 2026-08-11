package com.civicbridge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full Name
    @NotBlank(message = "Full name is required")
    @Column(nullable = false, length = 100)
    private String fullName;

    // Email
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Mobile Number
    @NotBlank(message = "Mobile number is required")
    @Column(unique = true, length = 15)
    private String mobile;

    // BCrypt Password
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    // User account enabled/disabled
    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;

    // User account locked/unlocked
    @Column(nullable = false)
    @Builder.Default
    private boolean accountLocked = false;

    // User Role
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}