package com.capgemini.complaintsmanagementsystem.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.capgemini.complaintsmanagementsystem.handler.SocketConnectionHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final SocketConnectionHandler socketHandler;

	public WebSocketConfig(SocketConnectionHandler socketHandler) {
		this.socketHandler = socketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler, "/chat").setAllowedOrigins("*");
	}
}