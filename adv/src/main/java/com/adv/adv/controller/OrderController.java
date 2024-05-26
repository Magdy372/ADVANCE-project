package com.adv.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adv.adv.model.Order;
import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository;
import com.adv.adv.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private userRepository userRepository;

    @PostMapping("/create")
    public Order createOrder(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            throw new RuntimeException("User is not logged in");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        return orderService.createOrder(user);
    }
}
