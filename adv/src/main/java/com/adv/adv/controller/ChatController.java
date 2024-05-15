package com.adv.adv.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/") // Added request mapping for "/chat"
public class ChatController { // Renamed to follow Java naming conventions
    @GetMapping("/chatting")
    public String chatting(Model model, HttpSession session) {
        // Retrieve username from session if available, otherwise set default
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "defaultUsername"; // Replace with actual default username
            session.setAttribute("username", username);
        }

        model.addAttribute("username", username);
        model.addAttribute("message", "defaultMessage"); // Replace with actual default message

        return "chatting";
    }
 
    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
    
    @MessageMapping("/addUser")
    @SendTo("/topic/chat")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
}