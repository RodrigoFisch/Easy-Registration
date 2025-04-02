package com.rodrigofisch.easyregistration.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "user")
public class RegisterPerson {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
}
