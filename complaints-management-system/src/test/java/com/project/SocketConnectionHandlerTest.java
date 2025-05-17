package com.project;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.handler.SocketConnectionHandler;
import com.capgemini.complaintsmanagementsystem.repository.ChatRepository;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import static org.mockito.Mockito.verify;

@SpringBootTest
class SocketConnectionHandlerTest {
	@MockBean
	private ChatRepository chatRepository;

	@MockBean
	private ComplaintRepository complaintRepository;

	@Autowired
	private SocketConnectionHandler socketHandler;

	@Test
	void testHandleTextMessage() throws Exception {

		WebSocketSession session = mock(WebSocketSession.class);
		URI uri = new URI("ws://localhost:8080/chat?compId=123");
		when(session.getUri()).thenReturn(uri);

		Complaint mockComplaint = new Complaint();
		mockComplaint.setComplaintId(123L);
		when(complaintRepository.findById(123L)).thenReturn(Optional.of(mockComplaint));

		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> messageMap = Map.of("from", "user1", "to", "admin", "content", "Test message", "compId",
				"123");
		String jsonMessage = mapper.writeValueAsString(messageMap);
		TextMessage textMessage = new TextMessage(jsonMessage);

		socketHandler.handleTextMessage(session, textMessage);

		verify(chatRepository).save(any(Chat.class));
	}
}
