package com.adv.adv.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Category;

import com.adv.adv.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CaregoryController {

     private CategoryRepository categoryrepository;
     @GetMapping({"","/"})
    public ModelAndView getAll() {
        ModelAndView mav =new ModelAndView("Categories.html");
        List<Category>categories=this.categoryrepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

}
