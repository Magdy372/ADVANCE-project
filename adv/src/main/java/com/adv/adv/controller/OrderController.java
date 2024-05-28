package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.model.Order;
import com.adv.adv.model.User;
import com.adv.adv.repository.OrderRepository;
import com.adv.adv.repository.userRepository;
import com.adv.adv.service.OrderService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller 
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public String  createOrder(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            throw new RuntimeException("User is not logged in");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        orderService.createOrder(user);
        return "redirect:/"; 

    }
    @GetMapping("/history")
    public ModelAndView getOrderHistory(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }
        List<Order> orders = orderService.getOrdersByUserId(userId);
        ModelAndView mav = new ModelAndView("history");
        mav.addObject("orders", orders);
        return mav;
    }
    @GetMapping("/orders/delete/{id}")
    @Transactional
    public RedirectView deleteOrder(@PathVariable("id") Long id) {
        orderRepository.deleteById(id);
        return new RedirectView("/");
    }
}
