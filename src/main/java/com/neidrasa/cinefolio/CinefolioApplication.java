package com.neidrasa.cinefolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CinefolioApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(CinefolioApplication.class, args);
	}

}
