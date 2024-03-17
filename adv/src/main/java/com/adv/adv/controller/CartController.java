package com.adv.adv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;



@RestController
public class CartController {
  @GetMapping("/cart")
public ModelAndView cart() {
      ModelAndView mv = new ModelAndView("cart.html");
          return mv;

}

}