package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Product;
import com.adv.adv.repository.ProductRepository;


@Controller
public class IndexController {
    // @GetMapping("/home")
    // public String home() {
    //     return "index";
    // }
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/home")
    public ModelAndView getNewProducts() {
        ModelAndView mav = new ModelAndView("index.html");
        
        List<Product> sortedByNewDates = this.productRepository.findAllByOrderByCreatedAtDesc();
        List<Product> sortedByRatingDesc = this.productRepository.findAllByOrderByRatingDesc();


        mav.addObject("newProducts", sortedByNewDates);

        mav.addObject("featuredProducts", sortedByRatingDesc);
     
        
        return mav;
    }
    
  
    
    
    
}
