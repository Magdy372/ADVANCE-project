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
        mav.addObject("user", user);
        mav.addObject("bindingResult", result); 
        return mav; 
    }


    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    user.setPassword(hashedPassword);
    user.setUserType(User.UserType.USER);

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
                
                session.setAttribute("id", user.getId());
                session.setAttribute("userType", user.getUserType());
                session.setAttribute("username", user.getUsername());
                
                mav.addObject("username", user.getUsername());
                if(user.getUserType()==User.UserType.ADMIN){
                    mav.setView(new RedirectView("/admin/dashboard"));
                } else {
                    mav.setView(new RedirectView("/"));
                }
                return mav;
            }
        }
    
      
        mav.setViewName("login.html");
        mav.addObject("error", "Invalid email or password");
        return mav;
    }
    
@GetMapping("/MyProfile")
public ModelAndView showProfile(HttpSession session) {
    ModelAndView mav = new ModelAndView();


    Long userId = (Long) session.getAttribute("id");

    if (userId != null) {
       
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            
            mav.addObject("user", user);
            
            mav.setViewName("MyProfile.html");
        } else {
           
            mav.setViewName("redirect:/login");
        }
    } else {
       
        mav.setViewName("redirect:/login");
    }

    return mav;
}


@PostMapping("/MyProfile")
public ModelAndView updateProfile(@Valid @ModelAttribute ("user") User user, BindingResult result,
                                  @RequestParam("username") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  HttpSession session) {
    
    Long userId = (Long) session.getAttribute("id");

if (result.hasErrors()) {
  ModelAndView mav = new ModelAndView("MyProfile.html");
  mav.addObject("user", user);
  mav.addObject("bindingResult", result); 
  return mav; 
}

    if (userId != null) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User EXuser = userOptional.get();
            EXuser.setUsername(username);
            EXuser.setEmail(email);
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            EXuser.setPassword(hashedPassword);
            EXuser.setConfirmPassword(hashedPassword);
            userRepository.save(EXuser); 
        }
    }

    ModelAndView modelAndView = new ModelAndView();
  
    modelAndView.setViewName("redirect:/");

    return modelAndView;
}




@PostMapping("/deleteAccount")
public ModelAndView deleteAccount(HttpSession session) {
    Long userId = (Long) session.getAttribute("id");
    ModelAndView mav = new ModelAndView();

    if (userId != null) {
        userRepository.deleteById(userId);
        session.invalidate();
        mav.setViewName("redirect:/");
    } else {
     
        mav.setViewName("redirect:/"); 
    }

    return mav;
}


@GetMapping("/logout")
public ModelAndView logout(HttpSession session) {
   
    session.invalidate();
    
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:/");

        
        return mv;
}


}
    
