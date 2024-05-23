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
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        List<Wishlist> wishlistItems = wishlistService.getItemsByUserId(userId);
        ModelAndView mav = new ModelAndView("wishlist.html");

        if (wishlistItems.isEmpty()) {
            mav.addObject("message", "Your wishlist is empty");
        } else {
            mav.addObject("wishlistItems", wishlistItems);
        }

        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addItem(@Valid @ModelAttribute("wishlist") Wishlist wishlistItem,
                                BindingResult result,
                                HttpSession session,
                                @RequestParam("productId") int productId) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("wishlist.html");
            mav.addObject("bindingResult", result);
            return mav;
        }

        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        boolean itemExists = wishlistService.doesItemExistForUser(userId, productId);
        if (itemExists) {
            ModelAndView mav = new ModelAndView("redirect:/wishlist");
            mav.addObject("message", "Item already exists in the wishlist");
            return mav;
        }

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId);

        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        wishlistService.addItem(wishlistItem);
        return new ModelAndView("redirect:/wishlist");
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
        Long userId = (Long) session.getAttribute("id");
        return wishlistService.getItemsByUserId(userId);
    }
}
