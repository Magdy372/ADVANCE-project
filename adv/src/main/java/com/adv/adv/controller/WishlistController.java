package com.adv.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.adv.adv.model.Product;
import com.adv.adv.repository.userRepository;
import com.adv.adv.model.User;
import com.adv.adv.model.Wishlist;
import com.adv.adv.repository.ProductRepository;
import com.adv.adv.repository.WishlistRepository;
import com.adv.adv.service.WishlistService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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

    @GetMapping({"","/"})
    public ModelAndView getAll() {
        ModelAndView mav =new ModelAndView("wishlist.html");
       // List<Product>products=this.productRepository.findAll();
        //mav.addObject("products", products);
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addItem(@Valid @ModelAttribute("wishlistItem") Wishlist wishlistItem,
                                BindingResult result,
                                @RequestParam("userId") int userId,
                                @RequestParam("productId") int productId) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("add-wishlist-item.html");
            // Add necessary model attributes if needed
            mav.addObject("bindingResult", result);
            return mav; // Return ModelAndView directly
        }

        // Retrieve User and Product objects from their respective repositories
        User user = userRepository.findById((long) userId)
                                   .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId);

        // Set the User and Product for the Wishlist item
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        Wishlist savedItem = wishlistService.addItem(wishlistItem);
        return new ModelAndView("redirect:/wishlist/all");
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable int itemId) {
        wishlistService.removeItem(itemId);
    }

    @GetMapping("/all")
    public List<Wishlist> getAllItems() {
        return wishlistService.getAllItems();
    }

    @GetMapping("/user/{userId}")
    public List<Wishlist> getItemsByUserId(@PathVariable int userId) {
        return wishlistService.getItemsByUserId(userId);
    }
}