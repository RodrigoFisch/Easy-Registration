package com.rodrigofisch.easyregistration.controller;

import com.rodrigofisch.easyregistration.controller.dto.LoginRequest;
import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import com.rodrigofisch.easyregistration.service.JwtService;
import com.rodrigofisch.easyregistration.service.RegisterPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/auth")  // Define o caminho base para todos os métodos neste controlador.
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("*")
public class AuthController {

    // Injeção das dependências
    private final RegisterPersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RegisterPersonService service;

    private final JwtService jwtService;

    @PostMapping("/register")  // Define que este método responde a requisições POST no caminho /auth/register
    public ResponseEntity<RegisterOutDto> register(@RequestBody @Valid RegisterInDto request) {
        log.info("Tentativa de registro: {}", request);
        RegisterOutDto response = service.create(request);
        return ResponseEntity.ok(response);
    }

    // Método para autenticar o usuário e gerar o token JWT
    @PostMapping("/login")  // Define que este método responde a requisições POST no caminho /auth/login
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Autenticação: Tentando fazer o login com e-mail e senha
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),   // O primeiro parâmetro é o e-mail
                        loginRequest.getPassword() // O segundo parâmetro é a senha
                )
        );

        // Verificando se o usuário existe no banco de dados
        RegisterPerson user = personRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gerando o token JWT com as informações do usuário autenticado
        String jwtToken = jwtService.generateToken(user.getEmail());

        // Retornando o token JWT para o cliente
        return ResponseEntity.ok(jwtToken);
    }

}
