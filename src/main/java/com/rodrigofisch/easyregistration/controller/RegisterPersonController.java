package com.rodrigofisch.easyregistration.controller;

import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.service.RegisterPersonService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("register-person")
@Log4j2
public class RegisterPersonController {

    @Autowired
    private RegisterPersonService service;

    //Pode ser utilizado futuramente para cadastros manuais
    @PostMapping
    public ResponseEntity<RegisterOutDto> create(@RequestBody @Valid RegisterInDto registerInDto){
        log.info("Cadastro realizado: {}", registerInDto);
        RegisterOutDto registerOutDto = service.create(registerInDto);

        return ResponseEntity.ok(registerOutDto);
    }

    @PutMapping("cpf/{cpf}")
    public ResponseEntity<RegisterOutDto> updateByCpf(@PathVariable("cpf") String cpf, @RequestBody RegisterInDto registerInDto){
        log.info("Cadastro atualizado: {}, {}", cpf, registerInDto);
        RegisterOutDto upDateDto = service.updateByCpf(cpf, registerInDto);
        return ResponseEntity.ok(upDateDto);
    }

    @DeleteMapping("cpf/{cpf}")
    public ResponseEntity<String> delete(@PathVariable("cpf") String cpf){
        log.info("Cadastro excluido: {}", cpf);
        String result = service.delete(cpf);
        return ResponseEntity.ok(result);
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity <RegisterOutDto>  consultByCpf(@PathVariable("cpf") String cpf){
        log.info("Consulta: {}", cpf);
        RegisterOutDto result = service.consultByCpf(cpf);
        return ResponseEntity.ok(result);
    }

    @GetMapping("all")
    public ResponseEntity<List<RegisterOutDto>> readRegisteres(){
        log.info("Lista de usuários registrados");
        List<RegisterOutDto> registers = service.readRegisteres();
        return ResponseEntity.ok(registers);
    }


}
