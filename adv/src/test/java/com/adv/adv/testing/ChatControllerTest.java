package com.adv.adv.testing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import com.adv.adv.controller.ChatController;
import com.adv.adv.controller.ChatMessage;

public class ChatControllerTest {
    
    private ChatController makeChatController() {
        return new ChatController();
    }

    @Test
    public void chatting_ReturnsCorrectViewName() {
        ChatController controller = makeChatController();
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("username")).thenReturn(null);

        // Act
        String viewName = controller.chatting(model, session);

        // Assert
        assertEquals("chatting", viewName);
    }

    @Test
    public void sendMessage_ReturnsSameMessage() {
        ChatController controller = makeChatController();
        ChatMessage inputMessage = new ChatMessage();
        inputMessage.setSender("user");
        inputMessage.setContent("Hello");

        // Act
        ChatMessage returnedMessage = controller.sendMessage(inputMessage);

        // Assert
        assertEquals(inputMessage, returnedMessage);
    }

  
    @Test
    public void addUser_UpdatesSessionAttributes() {
        ChatController controller = new ChatController();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("newUser");
        SimpMessageHeaderAccessor headerAccessor = mock(SimpMessageHeaderAccessor.class);
        Map<String, Object> sessionAttributes = new HashMap<>();
        when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

        // Act
        controller.addUser(chatMessage, headerAccessor);

        // Assert
        assertEquals("newUser", sessionAttributes.get("username"));
    }
}