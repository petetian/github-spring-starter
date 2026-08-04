package com.example.copilot.chat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/copilot")
public class CopilotChatController {

	private final CopilotChatService chatService;

	public CopilotChatController(CopilotChatService chatService) {
		this.chatService = chatService;
	}

	@PostMapping("/chat")
	@ResponseStatus(HttpStatus.OK)
	ChatResponse chat(@Valid @RequestBody ChatRequest request) throws Exception {
		return new ChatResponse(chatService.chat(request.prompt()));
	}

	record ChatRequest(@NotBlank @Size(max = 20_000) String prompt) {
	}

	record ChatResponse(String content) {
	}

}