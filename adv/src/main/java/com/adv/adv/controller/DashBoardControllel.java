package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private  userRepository userRepository;
 

    //   @Autowired
    // private OrderService orderService;
       @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/dashboard")
    public ModelAndView getadmindashboard() {
        ModelAndView mav = new ModelAndView("DashBoard.html");
        
        List<Product> sortedByNewDates = this.productRepository.findAllByOrderByCreatedAtDesc();
        List<User> users = this.userRepository.findAll();

        mav.addObject("newProducts", sortedByNewDates);
        mav.addObject("users", users);
     
        
        return mav;
    }

  
    
  @GetMapping("/logout")
public ModelAndView logout(HttpSession session) {
   
    session.invalidate();
    
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:/");

        
        return mv;
}
    
    @GetMapping("/OrderDetails")
    public ModelAndView getOrderDetails() {
    ModelAndView mav = new ModelAndView("OrderDetails.html");
    List<Order> orders = this.orderRepository.findAll();
    mav.addObject("orders", orders);
    return mav;
}
    @GetMapping("/orders/delete/{id}")
    @Transactional
    public RedirectView deleteOrder(@PathVariable("id") Long id) {
    orderRepository.deleteById(id);
    return new RedirectView("/admin/OrderDetails");
}
    
}
