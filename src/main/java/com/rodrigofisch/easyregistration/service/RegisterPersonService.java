package com.rodrigofisch.easyregistration.service;

import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterPersonService {

    @Autowired
    private RegisterPersonRepository personRepository;

    @Autowired
    private ModelMapper mapper;

    public RegisterOutDto create (RegisterInDto register){
        RegisterPerson registerPerson = mapper.map(register, RegisterPerson.class);
        personRepository.save(registerPerson);
        RegisterOutDto registerOutDto = mapper.map(registerPerson, RegisterOutDto.class);
        return registerOutDto;
    }
}
