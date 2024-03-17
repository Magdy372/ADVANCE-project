package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Product;
import com.adv.adv.repository.CategoryRepository;
import com.adv.adv.repository.MetalRepository;
import com.adv.adv.repository.ProductRepository;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {
    // @GetMapping("/home")
    // public String home() {
    //     return "index";
    // }
    
    @Autowired
    private MetalRepository metalRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("index.html");
        
        List<Product> sortedByNewDates = this.productRepository.findTop3ByOrderByIdDesc();

        List<Product> sortedByRatingDesc = this.productRepository.findAllByOrderByRatingDesc();
      

        mav.addObject("newProducts", sortedByNewDates);

        mav.addObject("featuredProducts", sortedByRatingDesc);
     
        
        return mav;
    }
    @GetMapping("/shop")
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("shop.html");
        
        List<Product> products = this.productRepository.findAll();

  
      

        mav.addObject("products", products);

     
     
        
        return mav;
    }


@GetMapping("/products-category/{id}")
public ModelAndView GetProductByCataegory(@PathVariable("id")int ID) {
    List<Product> products = this.productRepository.findAllByCategoryId(ID);
    ModelAndView mav =new ModelAndView("shop.html");
    mav.addObject("products", products);
  
    return mav;
}
    
    
}





