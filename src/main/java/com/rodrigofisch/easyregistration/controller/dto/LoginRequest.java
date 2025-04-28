package com.rodrigofisch.easyregistration.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "Email is required")
    @Email(message = "Insira um E-mail válido")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;
}
