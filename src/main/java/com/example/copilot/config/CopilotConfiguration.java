package com.example.copilot.config;

import com.github.copilot.CopilotClient;
import com.github.copilot.rpc.CopilotClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration(proxyBeanMethods = false)
public class CopilotConfiguration {

	@Bean(destroyMethod = "close")
	CopilotClient copilotClient(CopilotProperties properties) {
		var options = new CopilotClientOptions();
		if (StringUtils.hasText(properties.getCliPath())) {
			options.setCliPath(properties.getCliPath());
		}
		return new CopilotClient(options);
	}

}