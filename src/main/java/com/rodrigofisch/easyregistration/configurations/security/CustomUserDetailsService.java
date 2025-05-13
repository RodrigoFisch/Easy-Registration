package com.rodrigofisch.easyregistration.configurations.security;

import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterPersonRepository personRepository;

    public CustomUserDetailsService(RegisterPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisterPerson person = personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(person.getEmail(), person.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }
}
