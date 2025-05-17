package com.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.capgemini.complaintsmanagementsystem.handler.SocketConnectionHandler;
import com.capgemini.complaintsmanagementsystem.websocket.WebSocketConfig;

@SpringBootTest
class WebSocketConfigTest {

	@Autowired
	private WebSocketConfig webSocketConfig;

	@Test
	void testWebSocketConfigCreation() {
		assertNotNull(webSocketConfig);
	}

	@Test
	void testRegisterWebSocketHandlers() {

		WebSocketHandlerRegistry registry = mock(WebSocketHandlerRegistry.class);

		when(registry.addHandler(any(), anyString())).thenReturn((WebSocketHandlerRegistration) registry);
		when(((WebSocketHandlerRegistration) registry).setAllowedOrigins(anyString()))
				.thenReturn((WebSocketHandlerRegistration) registry);

		webSocketConfig.registerWebSocketHandlers(registry);

		verify(registry).addHandler(any(SocketConnectionHandler.class), eq("/chat"));
		((WebSocketHandlerRegistration) verify(registry)).setAllowedOrigins(eq("*"));
	}
}
