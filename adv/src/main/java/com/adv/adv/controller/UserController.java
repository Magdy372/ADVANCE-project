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

    // @GetMapping("/chat")
    // public String chat() {
    //     return "chat.html";
    // }


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
                session.setAttribute("id", user.getId());
                session.setAttribute("username", user.getUsername());
                // Pass user's name to the view
                mav.addObject("username", user.getUsername());
                if(user.getUserType()==User.UserType.ADMIN){
                    mav.setView(new RedirectView("/admin/dashboard"));
                } else {
                    mav.setView(new RedirectView("/"));
                }
                return mav;
            }
        }
    
        // If login fails, redirect to login page with an error message
        mav.setViewName("login.html");
        mav.addObject("error", "Invalid email or password");
        return mav;
    }
    
@GetMapping("/MyProfile")
public ModelAndView showProfile(HttpSession session) {
    ModelAndView mav = new ModelAndView();

    // Retrieve user ID from session
    Long userId = (Long) session.getAttribute("id");

    if (userId != null) {
        // Retrieve user from the database using the ID
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Pass the user's name and email and password to the view
            mav.addObject("user", user);
            
            mav.setViewName("MyProfile.html");
        } else {
            // If user not found, redirect to login page
            mav.setViewName("redirect:/login");
        }
    } else {
        // If no user ID found in session, redirect to login page
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
  mav.addObject("user", user); // Add the user object to retain form values
  mav.addObject("bindingResult", result); // Add the binding result
  return mav; // Return the registration view with errors
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
            userRepository.save(EXuser); // Update the user's profile
        }
    }

    ModelAndView modelAndView = new ModelAndView();
    // Set the view name to redirect the user to the home page
    modelAndView.setViewName("redirect:/");

    return modelAndView;
}




@PostMapping("/deleteAccount")
public ModelAndView deleteAccount(HttpSession session) {
    Long userId = (Long) session.getAttribute("id");
    ModelAndView mav = new ModelAndView();

    if (userId != null) {
        userRepository.deleteById(userId);
        session.invalidate(); // Invalidate the session after deleting the account
        mav.setViewName("redirect:/"); // Redirect to the login page
    } else {
        // Handle error scenario (e.g., user not found)
        mav.setViewName("redirect:/"); // Redirect to the home page or any other page as needed
    }

    return mav;
}


@GetMapping("/logout")
public ModelAndView logout(HttpSession session) {
    // Invalidate the session
    session.invalidate();
    // Redirect to the login page or any other desired page after logout
    ModelAndView mv = new ModelAndView("index.html");

        
        return mv;
}


}
    
