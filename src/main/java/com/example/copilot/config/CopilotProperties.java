package com.example.copilot.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("github.copilot")
public class CopilotProperties {

	private String cliPath;
	private String model;
	private Duration timeout = Duration.ofSeconds(60);

	public String getCliPath() {
		return cliPath;
	}

	public void setCliPath(String cliPath) {
		this.cliPath = cliPath;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Duration getTimeout() {
		return timeout;
	}

	public void setTimeout(Duration timeout) {
		this.timeout = timeout;
	}

}