package com.project;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;

import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketIntegrationTest {

	@LocalServerPort
	private int port;

	private WebSocketStompClient stompClient;
	private BlockingQueue<Map<String, Object>> blockingQueue;

	@BeforeEach
	void setup() {
		this.blockingQueue = new ArrayBlockingQueue<>(1);
		this.stompClient = new WebSocketStompClient(
				new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
	}

	@Test
	void testChatMessageExchange() throws Exception {
		StompSession session = stompClient
				.connect(String.format("ws://localhost:%d/chat?compId=123", port), new StompSessionHandlerAdapter() {
				}).get(1, TimeUnit.SECONDS);

		session.subscribe("/chat", new StompFrameHandler() {
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Map.class;
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) payload;
				blockingQueue.add(map);
			}
		});

		Map<String, String> message = Map.of("from", "testUser", "to", "admin", "content", "Test message", "compId",
				"123");
		session.send("/chat", message);

		await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
			Map<String, Object> received = blockingQueue.poll();
			assertNotNull(received);
			assertEquals("testUser", received.get("from"));
			assertEquals("Test message", received.get("content"));
		});
	}
}
