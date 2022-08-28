package com.cryptoportfoliotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.service.cryptoportfoliotracker.logic","com.service.cryptoportfoliotracker.repository"})
//@EnableJpaRepositories(basePackages = {"com.service.cryptoportfoliotracker.repository"})
public class CryptoportfoliotrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoportfoliotrackerApplication.class, args);
	}

}
