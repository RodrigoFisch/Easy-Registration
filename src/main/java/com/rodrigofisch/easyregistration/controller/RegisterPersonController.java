package com.rodrigofisch.easyregistration.controller;

import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.service.RegisterPersonService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("register-person")
@Log4j2
public class RegisterPersonController {

    @Autowired
    private RegisterPersonService service;

    @PostMapping
    public ResponseEntity<RegisterOutDto> create(@RequestBody @Valid RegisterInDto registerInDto){
        log.info("Cadastro realizado: {}", registerInDto);
        RegisterOutDto registerOutDto = service.create(registerInDto);

        return ResponseEntity.ok(registerOutDto);
    }
}
