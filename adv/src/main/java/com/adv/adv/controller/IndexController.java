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
        
        // Retrieve new products
        List<Product> newProducts = this.productRepository.findByType("new");
        // Retrieve featured products
        List<Product> featuredProducts = this.productRepository.findByType("fea");
        List<Product> SaleProducts = this.productRepository.findByType("sale");

        
        // Add new products to the model
        mav.addObject("newProducts", newProducts);
        // Add featured products to the model
        mav.addObject("featuredProducts", featuredProducts);
        mav.addObject("saleProducts", SaleProducts);
        
        return mav;
    }
    
    
    
}
