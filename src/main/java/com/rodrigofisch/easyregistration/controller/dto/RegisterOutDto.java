package com.rodrigofisch.easyregistration.controller.dto;

// Fornecer uma visão completa do recurso, incluindo informações adicionais, como o id gerado.
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterOutDto {

    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
}
