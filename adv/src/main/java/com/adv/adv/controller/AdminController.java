package com.adv.adv.controller;



import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.model.User;
import com.adv.adv.repository.userRepository; // Adjust the import statement

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    private  userRepository userRepository;

    @Autowired
    public AdminController(userRepository userRepository) {
        this.userRepository = userRepository;
    }

@GetMapping("/addAdmin")
    public ModelAndView showAddAdminForm() {
        User newAdmin = new User();
        ModelAndView mav = new ModelAndView("addAdmin.html");
        mav.addObject("admin", newAdmin);
        return mav;
    }

    @PostMapping("/addAdmin")
    public RedirectView addAdmin(@ModelAttribute("admin") User user) {
        // Set the user type to "admin" before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        user.setUserType(User.UserType.ADMIN);
        userRepository.save(user);
    
        
    
        return new RedirectView("/adminPage/addAdmin");
    }
    
}
