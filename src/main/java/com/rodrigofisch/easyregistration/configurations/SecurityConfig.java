package com.rodrigofisch.easyregistration.configurations;

import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable()) // Desativa CSRF (armazenamento de sessão de usuario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register-person").permitAll() // Rotas públicas
                        .anyRequest().authenticated() // Outras rotas precisam de autenticação
                )
                .httpBasic(withDefaults()); // Configuração de autenticação HTTP básica
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //autenticar os usuários com base nos dados armazenados no banco
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public UserDetailsService userDetailsService(RegisterPersonRepository personRepository) {
        return cpf -> {
            RegisterPerson person = personRepository.findByCpf(cpf)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            return new User(person.getEmail(), person.getPasswword(), List.of(new SimpleGrantedAuthority("USER")));
        };
    }

}
