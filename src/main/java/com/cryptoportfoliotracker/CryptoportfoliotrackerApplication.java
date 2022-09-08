package com.cryptoportfoliotracker;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration()
@SpringBootApplication(scanBasePackages={"com.cryptoportfoliotracker.logic"/*,"com.service.cryptoportfoliotracker.repository"*/,"com.cryptoportfoliotracker.entities"})
@EnableJpaRepositories(basePackages = {"com.cryptoportfoliotracker.repository"})
@ComponentScan({"com.cryptoportfoliotracker.repository","com.cryptoportfoliotracker.logic","com.cryptoportfoliotracker.entities"})
@Theme(value = "cryptoportfoliotracker", variant = Lumo.DARK)
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "@fontsource/heebo", version = "4.5.0")
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")
public class CryptoportfoliotrackerApplication extends SpringBootServletInitializer implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(CryptoportfoliotrackerApplication.class, args);
	}

}
