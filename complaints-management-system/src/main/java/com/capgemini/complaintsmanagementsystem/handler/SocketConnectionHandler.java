package com.capgemini.complaintsmanagementsystem.handler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.entity.Complaint;
import com.capgemini.complaintsmanagementsystem.repository.ChatRepository;
import com.capgemini.complaintsmanagementsystem.repository.ComplaintRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SocketConnectionHandler extends TextWebSocketHandler {

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private ComplaintRepository complaintRepository;

	private final Map<String, List<WebSocketSession>> complaintSessions = new ConcurrentHashMap<>();

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String compId = getComplaintId(session);
		if (compId == null) {
			session.close(CloseStatus.BAD_DATA);
			return;
		}

		complaintSessions.computeIfAbsent(compId, k -> Collections.synchronizedList(new ArrayList<>())).add(session);
		System.out.println("Connected to compId: " + compId);

		// Send recent messages wrapped as JSON
		Long complaintId = Long.parseLong(compId);
		List<Chat> recentMessages = chatRepository.findByComplaint_ComplaintIdOrderByChatTimestampDesc(complaintId);
		for (int i = recentMessages.size() - 1; i >= 0; i--) {
			Chat msg = recentMessages.get(i);

			String outgoingJson = objectMapper.writeValueAsString(Map.of("from", msg.getChatSender(), "to",
					msg.getChatReceiver(), "content", msg.getChatMessage(), "compId", compId));
			System.out.println(outgoingJson);
			session.sendMessage(new TextMessage(outgoingJson));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String compId = getComplaintId(session);
		if (compId != null) {
			complaintSessions.getOrDefault(compId, new ArrayList<>()).remove(session);
		}
		System.out.println("Disconnected from compId: " + compId);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		Map<String, String> msg = objectMapper.readValue(message.getPayload(), Map.class);

		String fromUser = msg.get("from");
		String toUser = msg.get("to");
		String content = msg.get("content");
		String compId = msg.get("compId");

		if (compId == null || fromUser == null || toUser == null || content == null) {
			session.sendMessage(new TextMessage("Invalid message format."));
			return;
		}

		Long complaintId = Long.parseLong(compId);
		Complaint complaint = complaintRepository.findById(complaintId)
				.orElseThrow(() -> new RuntimeException("Complaint not found"));

		Chat savedMessage = new Chat(complaint, fromUser, toUser, content, LocalDateTime.now());
		chatRepository.save(savedMessage);

		for (WebSocketSession s : complaintSessions.getOrDefault(compId, new ArrayList<>())) {
			if (s.isOpen()) {
				String outgoingJson = objectMapper.writeValueAsString(
						Map.of("from", fromUser, "to", toUser, "content", content, "compId", compId));
				s.sendMessage(new TextMessage(outgoingJson));
			}
		}
	}

	private String getComplaintId(WebSocketSession session) {
		String query = session.getUri().getQuery();
		if (query != null) {
			for (String param : query.split("&")) {
				String[] pair = param.split("=", 2);
				if (pair.length == 2 && pair[0].equals("compId")) {
					return pair[1];
				}
			}
		}
		return null;
	}

	private String getToken(WebSocketSession session) {
		String query = session.getUri().getQuery();
		if (query != null) {
			for (String param : query.split("&")) {
				String[] pair = param.split("=", 2);
				if (pair.length == 2 && pair[0].equals("token")) {
					return pair[1];
				}
			}
		}
		return null;
	}
}
