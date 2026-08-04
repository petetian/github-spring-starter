package com.example.copilot.chat;

import java.util.concurrent.CompletableFuture;

import com.example.copilot.config.CopilotProperties;
import com.github.copilot.CopilotClient;
import com.github.copilot.rpc.MessageOptions;
import com.github.copilot.rpc.PermissionRequestResult;
import com.github.copilot.rpc.PermissionRequestResultKind;
import com.github.copilot.rpc.SessionConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CopilotChatService {

	private final CopilotClient client;
	private final CopilotProperties properties;

	public CopilotChatService(CopilotClient client, CopilotProperties properties) {
		this.client = client;
		this.properties = properties;
	}

	public String chat(String prompt) throws Exception {
		client.start().get();

		var sessionConfig = new SessionConfig().setOnPermissionRequest((request, invocation) ->
				CompletableFuture.completedFuture(new PermissionRequestResult()
						.setKind(PermissionRequestResultKind.REJECTED)));
		if (StringUtils.hasText(properties.getModel())) {
			sessionConfig.setModel(properties.getModel());
		}

		try (var session = client.createSession(sessionConfig).get()) {
			var event = session.sendAndWait(
					new MessageOptions().setPrompt(prompt), properties.getTimeout().toMillis()).get();
			if (event == null || event.getData() == null) {
				throw new IllegalStateException("Copilot completed without an assistant response");
			}
			return event.getData().content();
		}
	}

}