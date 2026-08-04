package com.example.copilot.chat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CopilotChatController.class)
class CopilotChatControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CopilotChatService chatService;

	@Test
	void returnsCopilotResponse() throws Exception {
		when(chatService.chat("Explain records")).thenReturn("Records are immutable data carriers.");

		mockMvc.perform(post("/api/copilot/chat")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"prompt\":\"Explain records\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content").value("Records are immutable data carriers."));

		verify(chatService).chat("Explain records");
	}

	@Test
	void rejectsBlankPrompt() throws Exception {
		mockMvc.perform(post("/api/copilot/chat")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"prompt\":\" \"}"))
				.andExpect(status().isBadRequest());
	}

}