package com.adv.adv.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AddUser {

    @Autowired
    private userRepository userRepository;

    @GetMapping({"","/"})
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("UserList.html");
        List<User> users = this.userRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView addUserForm() {
        ModelAndView mav = new ModelAndView("AddUser.html");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("AddUser.html");
        }
        this.userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        ModelAndView mav = new ModelAndView("editUser.html");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public RedirectView editUser(@ModelAttribute("user") User user) {
        this.userRepository.save(user);
        return new RedirectView("/users");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteUser(@PathVariable("id") Long id) {
        this.userRepository.deleteById(id);
        return new RedirectView("/users");
    }

    @GetMapping("/details/{id}")
    public ModelAndView getUserDetails(@PathVariable("id") Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        ModelAndView mav = new ModelAndView("User-details.html");
        mav.addObject("user", user);
        return mav;
    }


}
