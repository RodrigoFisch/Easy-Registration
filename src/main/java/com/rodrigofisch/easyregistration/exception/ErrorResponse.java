package com.rodrigofisch.easyregistration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String statusMessage;
    private String errorMessage;
}
