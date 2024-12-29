package com.rodrigofisch.easyregistration.controller.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//FReceber os dados do cliente que são necessários para a criação ou atualização do recurso. O sistema gera o ID.
@Getter
@Setter
@ToString
public class RegisterInDto {

    @NotBlank(message = "Nome obrigatório")
    @Size(min= 1, max = 100, message = "Limiti máximo de caracteres execido")
    private String name;

    @NotBlank(message = "CPF obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    private String cpf;

    @NotBlank(message = "E-mail obrigatório")
    @Size(min = 1, max = 50, message = "Limiti máximo de caracteres execido")
    @Email(message = "E-mail inválido")
    private String email;
}
