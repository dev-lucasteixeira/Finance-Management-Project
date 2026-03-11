package com.lucasteixeira.finance_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@Modulith
@SpringBootApplication
public class FinanceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceManagementApplication.class, args);
	}

}
