package com.example.recuitment;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Authorization", description = "Append the prefix 'Bearer(space)' to the access token you receive from the login response.", scheme = "Basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class RecuitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuitmentApplication.class, args);
	}

}
