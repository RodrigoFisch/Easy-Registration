package com.rodrigofisch.easyregistration.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorResponse> handleRegisterPersonException(RegisterPersonException ex){
        RegisterPersonErrorEnum errorEnum = ex.getErrorEnum();
        ErrorResponse errorResposne = new ErrorResponse(
                errorEnum.getHttpStatus().value(),
                errorEnum.getHttpStatus().getReasonPhrase(),
                errorEnum.getMessage()
        );
        return new ResponseEntity<ErrorResponse>(errorResposne, errorEnum.getHttpStatus());
    }

    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Erro inesperado. Por favor, entre em contato com o suporte."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


