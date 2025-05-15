package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.repository.ChatRepository;
import com.capgemini.complaintsmanagementsystem.service.ChatServiceImpl;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {
    
    @Mock
    private ChatRepository chatRepository;
    
    @Mock
    private SimpMessagingTemplate messagingTemplate;
    
    @InjectMocks
    private ChatServiceImpl chatService;
    
    @Test
    void testGetChatHistoryByComplaintId() {
        Chat chat = new Chat();
        chat.setChatId(1L);
        chat.setComplaintId(1L);
        chat.setChatSender("user1");
        chat.setChatReceiver("admin");
        chat.setChatMessage("Test message");
        chat.setChatTimestamp(LocalDateTime.now());
        
        List<Chat> chatList = Arrays.asList(chat);
        
        when(chatRepository.findByComplaintIdOrderByChatTimestampAsc(1L)).thenReturn(chatList);
        
        List<Chat> result = chatService.getChatHistoryByComplaintId(1L);
        assertEquals(1, result.size());
        assertEquals("Test message", result.get(0).getChatMessage());
        assertEquals("user1", result.get(0).getChatSender());
        assertEquals("admin", result.get(0).getChatReceiver());
    }
        
    @Test
    void testSaveAndSendMessage() {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO(1L, "Test message", "user1", "admin");
        
        Chat chat = new Chat();
        chat.setChatId(1L);
        chat.setComplaintId(1L);
        chat.setChatSender("user1");
        chat.setChatReceiver("admin");
        chat.setChatMessage("Test message");
        chat.setChatTimestamp(LocalDateTime.now());
        
        when(chatRepository.save(org.mockito.ArgumentMatchers.any(Chat.class))).thenReturn(chat);
        
        Chat result = chatService.saveAndSendMessage(chatMessageDTO);
        assertEquals("Test message", result.getChatMessage());
        assertEquals("user1", result.getChatSender());
        assertEquals("admin", result.getChatReceiver());
    }
    
    @Test
    void testAddChatMessage() {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO(1L, "Test message", "user1", "admin");
        
        Chat chat = new Chat();
        chat.setChatId(1L);
        chat.setComplaintId(1L);
        chat.setChatSender("user1");
        chat.setChatReceiver("admin");
        chat.setChatMessage("Test message");
        chat.setChatTimestamp(LocalDateTime.now());
        
        when(chatRepository.save(org.mockito.ArgumentMatchers.any(Chat.class))).thenReturn(chat);
        
        Chat result = chatService.addChatMessage(chatMessageDTO);
        assertEquals("Test message", result.getChatMessage());
        assertEquals("user1", result.getChatSender());
        assertEquals("admin", result.getChatReceiver());
    }
    
    @Test
    void testGetMessagesBySender() {
        Chat chat = new Chat();
        chat.setChatId(1L);
        chat.setComplaintId(1L);
        chat.setChatSender("user1");
        chat.setChatReceiver("admin");
        chat.setChatMessage("Test message");
        chat.setChatTimestamp(LocalDateTime.now());
        
        List<Chat> chatList = Arrays.asList(chat);
        
        when(chatRepository.findByChatSenderOrderByChatTimestampDesc("user1")).thenReturn(chatList);
        
        List<Chat> result = chatService.getMessagesBySender("user1");
        assertEquals(1, result.size());
        assertEquals("Test message", result.get(0).getChatMessage());
        assertEquals("user1", result.get(0).getChatSender());
    }
    
    
    @Test
    void testGetAllMessages() {
        Chat chat1 = new Chat();
        chat1.setChatId(1L);
        chat1.setComplaintId(1L);
        chat1.setChatSender("user1");
        chat1.setChatReceiver("admin");
        chat1.setChatMessage("Hello");
        chat1.setChatTimestamp(LocalDateTime.now().minusHours(1));
        
        Chat chat2 = new Chat();
        chat2.setChatId(2L);
        chat2.setComplaintId(1L);
        chat2.setChatSender("admin");
        chat2.setChatReceiver("user1");
        chat2.setChatMessage("Hi there");
        chat2.setChatTimestamp(LocalDateTime.now());
        
        List<Chat> chatList = Arrays.asList(chat2, chat1); 
        
        when(chatRepository.findAllByOrderByChatTimestampDesc()).thenReturn(chatList);
        
        List<Chat> result = chatService.getAllMessages();
        assertEquals(2, result.size());
        assertEquals("Hi there", result.get(0).getChatMessage()); 
        assertEquals("Hello", result.get(1).getChatMessage());
    }
}
