package com.cryptoportfoliotracker.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class CryptoportfoliotrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoportfoliotrackerApplication.class, args);
	}

}
