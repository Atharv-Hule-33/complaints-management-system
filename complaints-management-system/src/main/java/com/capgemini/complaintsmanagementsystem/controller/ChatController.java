package com.capgemini.complaintsmanagementsystem.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.service.ChatService;




import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private ChatService chatService;


    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    
    

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        log.debug("Request received to send the message:{}",chatMessageDTO);
        chatService.saveAndSendMessage(chatMessageDTO);
    }
    
    @GetMapping("/{complaintId}")
    public List<Chat> getChatHistory(@PathVariable Long complaintId) {
        log.debug("Request received to get the history of chat by ID:{}",complaintId);
        return chatService.getChatHistoryByComplaintId(complaintId);
    }
    
    @GetMapping("/between/{sender}/{receiver}")
    public List<Chat> getChatHistoryBetweenUsers(
            @PathVariable String sender, 
            @PathVariable String receiver) {
        log.debug("Request received to get chat histories between users :{}",sender);
        return chatService.getChatHistoryBetweenUsers(sender, receiver);
    }
   
    @PostMapping("/addMessage")
    @ResponseBody
    public Chat addChatMessage(@Valid @RequestBody ChatMessageDTO chatMessageDTO) {
        return chatService.addChatMessage(chatMessageDTO);
    }
    
    @GetMapping("/sender/{sender}")
    public ResponseEntity<List<Chat>> getMessagesBySender(@PathVariable String sender) {
        List<Chat> messages = chatService.getMessagesBySender(sender);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/allMessages")
    @ResponseBody
    public ResponseEntity<List<Chat>> getAllMessages() {
        List<Chat> messages = chatService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


}
