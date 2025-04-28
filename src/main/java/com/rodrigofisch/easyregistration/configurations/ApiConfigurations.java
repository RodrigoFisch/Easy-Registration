package com.rodrigofisch.easyregistration.configurations;

import com.rodrigofisch.easyregistration.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfigurations {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}
