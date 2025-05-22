package com.rodrigofisch.easyregistration.configurations.security;

import com.rodrigofisch.easyregistration.exception.RegisterPersonErrorEnum;
import com.rodrigofisch.easyregistration.exception.RegisterPersonException;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    private static final String PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public void validate(String password) {
        if (password == null || !password.matches(PATTERN)) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.INVALID_PASSWORD);
        }
    }
}
