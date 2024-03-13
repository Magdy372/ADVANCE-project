package com.adv.adv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProfileController {
    @GetMapping("/myProfile")
    public String Profile() {
      return "MyProfile"; 
    }

}
