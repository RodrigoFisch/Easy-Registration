package com.rodrigofisch.easyregistration.service;

import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.exception.RegisterPersonErrorEnum;
import com.rodrigofisch.easyregistration.exception.RegisterPersonException;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RegisterPersonService {

    @Autowired
    private RegisterPersonRepository personRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterOutDto create (RegisterInDto registerInDto){
        checkEmailAndCpf(registerInDto);

        RegisterPerson registerPerson = mapper.map(registerInDto, RegisterPerson.class);

        registerPerson.setPassword(passwordEncoder.encode(registerInDto.getPassword()));
        try {
            personRepository.save(registerPerson);
        } catch (DataIntegrityViolationException e) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.REGISTRATION_FAILED, e);
        } catch (Exception e) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.UNEXPECTED_ERROR, e);
        }
        // Mapeia a entidade para o DTO de saída
        return mapper.map(registerPerson, RegisterOutDto.class);
    }

    public RegisterOutDto updateByCpf(String cpf, RegisterInDto registerInDto){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        // Lança exceção se o CPF não for encontrado
        RegisterPerson existingPerson = optional.orElseThrow(() ->
                new RegisterPersonException(RegisterPersonErrorEnum.CPF_NOT_FOUND)
        );

        // Verifica se o novo CPF está sendo registrado por outra pessoa
        if (!cpf.equals(registerInDto.getCpf()) && personRepository.existsByCpf(registerInDto.getCpf())) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.CPF_ALREADY_REGISTERED);
        }
        // Verifica se o email está sendo usado por outro registro
        if (personRepository.existsByEmail(registerInDto.getEmail())) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.EMAIL_ALREADY_REGISTERED);
        }
        // Mapeia os dados para atualizar o registro
        mapper.map(registerInDto, existingPerson);

        try {
            // Salva o registro atualizado
            personRepository.save(existingPerson);
        } catch (Exception e) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.UPDATE_FAILED, e);
        }

        // Mapeia o objeto atualizado para o DTO de saída
        RegisterOutDto registerOutDto = mapper.map(existingPerson, RegisterOutDto.class);

        return registerOutDto;
    }

    public RegisterOutDto consultByCpf(String cpf){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        // Se não encontrar, lança exceção
        RegisterPerson cpfLookup = optional.orElseThrow(() ->
                new RegisterPersonException(RegisterPersonErrorEnum.CPF_NOT_FOUND)
        );
        // Mapeia a entidade para o DTO de saída
        return mapper.map(cpfLookup, RegisterOutDto.class);
    }

    public String delete(String cpf){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        // Se não encontrar, lança exceção
        RegisterPerson cpfLookup = optional.orElseThrow(() ->
                new RegisterPersonException(RegisterPersonErrorEnum.CPF_NOT_FOUND)
        );
        try {
            // Deleta o registro
            personRepository.delete(cpfLookup);
        } catch (Exception e) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.DELETE_FAILED, e);
        }
        return ("Registro deletado com sucesso");
    }

    public List<RegisterOutDto> readRegisteres(){
        List<RegisterPerson> registerPersonList = personRepository.findAll();
        if (registerPersonList.isEmpty()) {
            return (List<RegisterOutDto>) ResponseEntity.noContent().build(); // Retorna 204 No Content se não houver registros
        }
        List<RegisterOutDto> registerOutDtoList = mapper.map(registerPersonList, new TypeToken<List<RegisterOutDto>>()
        {}.getType());
        return registerOutDtoList;
    }

    public void checkEmailAndCpf(RegisterInDto registerInDto){

        if (personRepository.existsByCpf(registerInDto.getCpf())) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.CPF_ALREADY_REGISTERED);
        }
        if (personRepository.existsByEmail(registerInDto.getEmail())) {
            throw new RegisterPersonException(RegisterPersonErrorEnum.EMAIL_ALREADY_REGISTERED);
        }
    }



}
