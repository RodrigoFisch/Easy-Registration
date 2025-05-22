package com.rodrigofisch.easyregistration.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RegisterPersonErrorEnum {

    INVALID_DATA(HttpStatus.BAD_REQUEST, "Dados da requisição incorretos."),
    REGISTRATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar o cadastro."),
    CPF_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "CPF cadastrado."),
    EMAIL_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "E-mail cadastrado" ),
    INVALID_UPDATE_DATA(HttpStatus.BAD_REQUEST, "Os dados fornecidos para a atualização são inválidos."),
    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar a atualização."),
    CPF_NOT_FOUND(HttpStatus.NOT_FOUND, "CPF não encontrado."),
    QUERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar a consulta."),
    QUERY_ALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar a consulta da lista."),
    DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível excluir o registro."),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"Senha deve conter minimo de 8 caracteres, incluir maiúscula," +
            "minúscula, número, símbolo.");

    private HttpStatus httpStatus;
    private String message;

    RegisterPersonErrorEnum(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
