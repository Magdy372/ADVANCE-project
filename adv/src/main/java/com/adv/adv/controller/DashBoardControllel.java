package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.model.Order;
import com.adv.adv.model.Product;
import com.adv.adv.model.User;
import com.adv.adv.repository.OrderRepository;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.repository.userRepository;
import com.adv.adv.service.OrderService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
@Controller
public class DashBoardControllel {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/dashboard")
    public ModelAndView getadmindashboard() {
        ModelAndView mav = new ModelAndView("DashBoard.html");
        
        List<Product> sortedByNewDates = this.productRepository.findAllByOrderByCreatedAtDesc();
        List<User> users = this.userRepository.findAll();
        List<Order> orders = this.orderRepository.findAll();
        int userCount = countUsers(); 
        int orderCount = countOrders(); 

        mav.addObject("newProducts", sortedByNewDates);
        mav.addObject("users", users);
        mav.addObject("userCount", userCount); 
        mav.addObject("orders", orders);
        mav.addObject("orderCount", orderCount); 

        
        return mav;
    }

    
    @GetMapping("/OrderDetails")
    public ModelAndView getOrderDetails() {
        ModelAndView mav = new ModelAndView("OrderDetails.html");
        List<Order> orders = this.orderRepository.findAll();
        mav.addObject("orders", orders);
        return mav;
    }
    
  
    
    private int countUsers() {
        String sql = "SELECT COUNT(*) FROM user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int countOrders() {
        String sql = "SELECT COUNT(*) FROM orders";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
