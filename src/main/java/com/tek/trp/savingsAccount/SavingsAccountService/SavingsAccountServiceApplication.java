package com.tek.trp.savingsAccount.SavingsAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SavingsAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavingsAccountServiceApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
