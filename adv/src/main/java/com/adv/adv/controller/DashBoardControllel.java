package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Product;
import com.adv.adv.repository.ProductRepository;

@RestController
@RequestMapping("/admin")
@Controller
public class DashBoardControllel {
    
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/dashboard")
    public ModelAndView getadmindashboard() {
        ModelAndView mav = new ModelAndView("DashBoard.html");
        
        List<Product> sortedByNewDates = this.productRepository.findAllByOrderByCreatedAtDesc();


        mav.addObject("newProducts", sortedByNewDates);

     
        
        return mav;
    }
    
  
    
    
    
}
