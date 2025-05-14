package com.capgemini.complaintsmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.complaintsmanagementsystem.Dto.ChatMessageDTO;
import com.capgemini.complaintsmanagementsystem.entity.Chat;
import com.capgemini.complaintsmanagementsystem.service.ChatService;

@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        chatService.saveAndSendMessage(chatMessageDTO);
    }
    
    @GetMapping("/{complaintId}")
    @ResponseBody
    public List<Chat> getChatHistory(@PathVariable Long complaintId) {
        return chatService.getChatHistoryByComplaintId(complaintId);
    }
    
    @GetMapping("/between/{sender}/{receiver}")
    @ResponseBody
    public List<Chat> getChatHistoryBetweenUsers(
            @PathVariable String sender, 
            @PathVariable String receiver) {
        return chatService.getChatHistoryBetweenUsers(sender, receiver);
    }
}
