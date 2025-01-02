package com.rodrigofisch.easyregistration.exception;

import lombok.Getter;

@Getter
public class RegisterPersonException extends RuntimeException{

    private RegisterPersonErrorEnum errorEnum;

    public RegisterPersonException(RegisterPersonErrorEnum errorEnum) {
        super(errorEnum.getMessage()); // Opcional: Configura a mensagem na superclasse
        this.errorEnum = errorEnum;
    }

    // Construtor que aceita o enum e uma causa (Throwable)
    public RegisterPersonException(RegisterPersonErrorEnum errorEnum, Throwable cause){
        super(errorEnum.getMessage(), cause); // Configura mensagem e causa na superclasse
        this.errorEnum = errorEnum;
    }

}
