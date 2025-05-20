package com.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.capgemini.complaintsmanagementsystem.handler.SocketConnectionHandler;
import com.capgemini.complaintsmanagementsystem.websocket.WebSocketConfig;

@SpringBootTest(classes = com.capgemini.complaintsmanagementsystem.ComplaintsManagementSystemApplication.class)
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
        WebSocketHandlerRegistration registration = mock(WebSocketHandlerRegistration.class);

       
        when(registry.addHandler(any(SocketConnectionHandler.class), eq("/chat"))).thenReturn(registration);
        when(registration.setAllowedOrigins(anyString())).thenReturn(registration);

      
        webSocketConfig.registerWebSocketHandlers(registry);

        verify(registry).addHandler(any(SocketConnectionHandler.class), eq("/chat"));
        verify(registration).setAllowedOrigins(eq("*"));
    }
}
