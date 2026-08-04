package com.example.copilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GithubCopilotSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubCopilotSpringApplication.class, args);
	}

}
