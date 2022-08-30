package com.cryptoportfoliotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@EnableAutoConfiguration()
@SpringBootApplication(scanBasePackages={"com.cryptoportfoliotracker.logic"/*,"com.service.cryptoportfoliotracker.repository"*/,"com.cryptoportfoliotracker.entities"})
@EnableJpaRepositories(basePackages = {"com.cryptoportfoliotracker.repository"})
@ComponentScan({"com.cryptoportfoliotracker.repository","com.cryptoportfoliotracker.logic","com.cryptoportfoliotracker.entities"})
public class CryptoportfoliotrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoportfoliotrackerApplication.class, args);
	}

}
