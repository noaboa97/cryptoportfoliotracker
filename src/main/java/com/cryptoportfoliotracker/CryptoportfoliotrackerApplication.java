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


/***
 * Entry point of the Spring Boot application
 *
 * @author Noah Li Wan Po
 * @version 1.0
 *
 * @EnableAutoConfiguration tries to guess and configure the beans needed for the project
 *
 * @ComponentScan packages that we want to be scanned and are needed for the application to run
 *
 * @Theme needed for Vaadin to load the appropriate theme and files for the theme
 *
 * @NpmPackage needed for Vaadin to load the needed dependencies which include the JS modules
 */
@EnableAutoConfiguration()
@SpringBootApplication(scanBasePackages = {"com.cryptoportfoliotracker.logic", "com.cryptoportfoliotracker.entities"})
@EnableJpaRepositories(basePackages = {"com.cryptoportfoliotracker.repository"})
@ComponentScan({"com.cryptoportfoliotracker.repository", "com.cryptoportfoliotracker.logic", "com.cryptoportfoliotracker.entities"})
@Theme(value = "cryptoportfoliotracker", variant = Lumo.DARK)
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "@fontsource/heebo", version = "4.5.0")
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")
public class CryptoportfoliotrackerApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(CryptoportfoliotrackerApplication.class, args);
    }

}
