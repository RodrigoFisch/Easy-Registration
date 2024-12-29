package com.rodrigofisch.easyregistration;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyRegistrationApplication.class, args);
	}

}
