package com.rodrigofisch.easyregistration.service;

import com.rodrigofisch.easyregistration.controller.dto.RegisterInDto;
import com.rodrigofisch.easyregistration.controller.dto.RegisterOutDto;
import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import com.rodrigofisch.easyregistration.repository.RegisterPersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterPersonService {

    @Autowired
    private RegisterPersonRepository personRepository;

    @Autowired
    private ModelMapper mapper;

    public RegisterOutDto create (RegisterInDto registerInDto){
        RegisterPerson registerPerson = mapper.map(registerInDto, RegisterPerson.class);
        personRepository.save(registerPerson);
        RegisterOutDto registerOutDto = mapper.map(registerPerson, RegisterOutDto.class);
        return registerOutDto;
    }

    public RegisterOutDto updateByCpf(String cpf, RegisterInDto registerInDto){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        RegisterPerson upDateRegister = optional.get();
        mapper.map(registerInDto, upDateRegister);
        personRepository.save(upDateRegister);
        RegisterOutDto registerOutDto = mapper.map(upDateRegister, RegisterOutDto.class);
        return registerOutDto;
    }

    public RegisterOutDto consultByCpf(String cpf){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        RegisterPerson cpfLookup = optional.get();
        RegisterOutDto registerOutDto = mapper.map(cpfLookup, RegisterOutDto.class);
        return registerOutDto;
    }

    public String delete(String cpf){
        Optional<RegisterPerson> optional = personRepository.findByCpf(cpf);
        personRepository.delete(optional.get());
        return "Registro deletado";
    }

    public List<RegisterOutDto> readRegisteres(){
        List<RegisterPerson> registerPersonList = personRepository.findAll();
        List<RegisterOutDto> registerOutDtoList = mapper.map(registerPersonList, new TypeToken<List<RegisterOutDto>>()
        {}.getType());
        return registerOutDtoList;
    }


}
