package com.civicbridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
    private String mobile;

    @NotBlank(message = "Role is required")
    private String role;

}