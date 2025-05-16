package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.controller.ChatController;
import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.service.ChatService;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

   /* @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatController chatController;

    private Chat testChat;

    @BeforeEach
    void setUp() {
        new ChatMessageDTO(1L, "Test message", "user1", "admin");
        
        testChat = new Chat();
        testChat.setChatId(1L);
        testChat.setComplaintId(1L);
        testChat.setChatSender("user1");
        testChat.setChatReceiver("admin");
        testChat.setChatMessage("Test message");
        testChat.setChatTimestamp(LocalDateTime.now());
    }

    @Test
    @DisplayName("Get Messages By Sender")
    void testGetMessagesBySender() {
        List<Chat> chatList = Arrays.asList(testChat);
        when(chatService.getMessagesBySender("user1")).thenReturn(chatList);
        
        ResponseEntity<List<Chat>> response = chatController.getMessagesBySender("user1");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("user1", response.getBody().get(0).getChatSender());
    }

    @Test
    @DisplayName("Get All Messages")
    void testGetAllMessages() {
        List<Chat> chatList = Arrays.asList(testChat);
        when(chatService.getAllMessages()).thenReturn(chatList);
        
        ResponseEntity<List<Chat>> response = chatController.getAllMessages();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getChatId());
    }
    */
}
