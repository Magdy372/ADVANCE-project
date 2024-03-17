package com.adv.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.model.Product;
import com.adv.adv.repository.userRepository;
import com.adv.adv.model.User;
import com.adv.adv.model.Wishlist;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.service.WishlistService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;



@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private userRepository userRepository;




    @GetMapping({"/",""})
    public ModelAndView getItemsByUserId(HttpSession session) {
        // Retrieve user ID from the session
       // long userId =  (long) session.getAttribute("id");
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }
        
        // Use the retrieved user ID to get wishlist items
        List<Wishlist> wishlistItems = wishlistService.getItemsByUserId(userId);
        
        // Create a new ModelAndView object
        ModelAndView mav = new ModelAndView("wishlist.html");
        
        // Add the wishlist items to the ModelAndView object
        mav.addObject("wishlistItems", wishlistItems);
        
        return mav;
    }



    @GetMapping("/add")
    public ModelAndView addItem(@Valid @ModelAttribute("wishlist") Wishlist wishlistItem,
                                BindingResult result,
                                HttpSession session,
                                @RequestParam("productId") int productId) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("wishlist.html");
            // Add necessary model attributes if needed
            mav.addObject("bindingResult", result);
            return mav; // Return ModelAndView directly
        }

        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        
        boolean itemExists = wishlistService.doesItemExistForUser(userId, productId);
        if (itemExists) {
            // If the item already exists, you can handle this situation as needed
            // For example, redirect back to the wishlist with a message indicating that the item already exists
            ModelAndView mav = new ModelAndView("redirect:/");
            return mav;
        }

        // Retrieve User and Product objects from their respective repositories
        User user = userRepository.findById((long) userId)
                                   .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId);

        // Set the User and Product for the Wishlist item
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        Wishlist savedItem = wishlistService.addItem(wishlistItem);
        return new ModelAndView("redirect:/");
    }



    @GetMapping("/remove/{itemId}")
    public ModelAndView removeItem(@PathVariable int itemId) {
        wishlistService.removeItem(itemId);
        return new ModelAndView("redirect:/wishlist");
    }


    @GetMapping("/all")
    public List<Wishlist> getAllItems() {
        return wishlistService.getAllItems();
    }


    @GetMapping("/user/{userId}")
    public List<Wishlist> getItemsByUserId(@PathVariable int userId) {
        return wishlistService.getItemsByUserId(userId);
    }


    @GetMapping("/user")
    public List<Wishlist> getItemsByUser(HttpSession session) {
        //return wishlistService.getItemsByUserId(userId);
        long userId =  (long) session.getAttribute("id");
        List<Wishlist> wishlistItems = wishlistService.getItemsByUserId(userId);
        return wishlistItems;
    }
    

    
}