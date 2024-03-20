package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.model.Category;
import com.adv.adv.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CaregoryController {
    @Autowired
     private CategoryRepository categoryrepository;
     @GetMapping({"","/"})
    public ModelAndView getAll() {
        ModelAndView mav =new ModelAndView("CategoriesList.html");
          mav.addObject("category", new Category());
        List<Category>categories=this.categoryrepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }
 //delete
    @GetMapping("/delete/{Id}")
    @Transactional
public RedirectView deleteCtaegory(@PathVariable("Id") int Id) {
    this.categoryrepository.deleteById(Id);
    return new RedirectView("/categories");
}

@PostMapping("/create")
public ModelAndView addCategory(@Valid @ModelAttribute("category") Category categor, BindingResult result,
                                @RequestParam("name") String name) {
    ModelAndView modelAndView = new ModelAndView();
    
    if (result.hasErrors()) {
        modelAndView.setViewName("CategoriesList.html");
        modelAndView.addObject("categories", categoryrepository.findAll());
        modelAndView.addObject("errors", result.getAllErrors());
        return modelAndView;
    }

    Category category = new Category();
    category.setName(name);
    this.categoryrepository.save(category);
    
    modelAndView.setViewName("redirect:/categories"); // Redirect to the categories page
    return modelAndView;
}


}