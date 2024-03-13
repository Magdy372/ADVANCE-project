package com.adv.adv.controller;

import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Optional;



@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private userRepository userRepository;

@GetMapping("/signup")
    public  ModelAndView showsignuppage (){
        User newuser= new User();
        ModelAndView mav = new ModelAndView("signup.html");
        mav.addObject("user",newuser);
        return mav;
    }
@PostMapping("/signup")
public  ModelAndView saveUser(@Valid @ModelAttribute User user,BindingResult result) {
    // Check if the email already exists in the database
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
        result.rejectValue("email", "error.user", "Email is already in use");
    }

    if (!user.getPassword().equals(user.getConfirmPassword())) {
      result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
  }

if (result.hasErrors()) {
        ModelAndView mav = new ModelAndView("signup.html");
        mav.addObject("user", user); // Add the user object to retain form values
        mav.addObject("bindingResult", result); // Add the binding result
        return mav; // Return the registration view with errors
    }

    // Hash the password before saving
    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    user.setPassword(hashedPassword);

    // Save the user to the database
    userRepository.save(user);

    // Redirect to the login page after successful signup
    ModelAndView mav = new ModelAndView("redirect:login");
    mav.addObject("user", user); // Optionally, you can pass the user object to the success page
    return mav;
}


    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        User newuser= new User();
        ModelAndView mv = new ModelAndView("login.html");
        mv.addObject("user",newuser);
        return mv;
    }
    


@PostMapping("/login")
public ModelAndView login(@ModelAttribute("user") User loginUser, Model model, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    // Check if email and password are provided
    if (loginUser.getEmail() == null || loginUser.getEmail().isEmpty() || 
        loginUser.getPassword() == null || loginUser.getPassword().isEmpty()) {
        mav.setViewName("login.html");
        mav.addObject("error", "Email and password are required");
        return mav;
    }

    Optional<User> userOptional = userRepository.findByEmail(loginUser.getEmail());

    if (userOptional.isPresent()) {
        User user = userOptional.get();

        if (BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            // Save user ID in the session
            session.setAttribute("username", user.getUsername());
            if(user.getUserType()==User.UserType.ADMIN){
                mav.setView(new RedirectView("/adminPage/addAdmin"));
            } else {
                mav.setView(new RedirectView("/home"));
            }
            return mav;
        }
    }

    // If login fails, redirect to login page with an error message
    mav.setViewName("login.html");
    mav.addObject("error", "Invalid email or password");
    return mav;
}





}
    
