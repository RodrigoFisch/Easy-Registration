package com.rodrigofisch.easyregistration.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable()) // Desativa CSRF (armazenamento de sessão de usuario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register-person/**").permitAll() // Rotas públicas
                        .anyRequest().authenticated() // Outras rotas precisam de autenticação
                )
                .httpBasic(withDefaults()); // Configuração de autenticação HTTP básica
        return http.build();
    }
}
