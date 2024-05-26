package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Product;
import com.adv.adv.model.User;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.repository.userRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
@Controller
public class DashBoardControllel {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  userRepository userRepository;

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
    
    
    
}
