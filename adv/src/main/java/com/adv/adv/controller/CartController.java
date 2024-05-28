package com.adv.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adv.adv.repository.userRepository;
import com.adv.adv.service.CartService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.adv.adv.model.Cart;
import com.adv.adv.model.Product;
import com.adv.adv.model.User;
import com.adv.adv.repository.CartRepository;
import com.adv.adv.repository.ProductRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private userRepository userRepository;

    @GetMapping({ "/", "" })
    public ModelAndView getItemsByUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        List<Cart> cartItems = cartService.getItemsByUserId(userId);
        ModelAndView mav = new ModelAndView("cart.html");

        if (cartItems.isEmpty()) {
            mav.addObject("message", "Your cart is empty");
        } else {
            double totalPrice = cartService.calculateTotalPrice(cartItems);
            mav.addObject("cartItems", cartItems);
            mav.addObject("totalPrice", totalPrice);
        }

        return mav;
    }

    // @GetMapping("/add")
    // public ModelAndView addItem(@Valid @ModelAttribute("cart") Cart cartItem,
    //                             BindingResult result,
    //                             HttpSession session,
    //                             @RequestParam("productId") int productId) {

    //     if (result.hasErrors()) {
    //         ModelAndView mav = new ModelAndView("cart.html");
    //         mav.addObject("bindingResult", result);
    //         return mav;
    //     }

    //     Long userId = (Long) session.getAttribute("id");
    //     if (userId == null) {
    //         return new ModelAndView("redirect:/login");
    //     }

    //     Product product = productRepository.findById(productId);
    //     User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    //     Cart existingCartItem = cartService.getCartItemByUserAndProduct(user, product);

    //     if (existingCartItem != null) {
    //         existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
    //         existingCartItem.setSub_total(existingCartItem.getQuantity() * product.getPrice());
    //         cartService.updateCartItem(existingCartItem);
    //     } else {
    //         cartItem.setUser(user);
    //         cartItem.setProduct(product);
    //         cartItem.setQuantity(1);
    //         cartItem.setSub_total(product.getPrice());
    //         cartService.addItem(cartItem);
    //     }

    //     return new ModelAndView("redirect:/");
    // }

    @PostMapping("/add")
    public ModelAndView addQuantity(@Valid @ModelAttribute("cart") Cart cartItem,
                                    BindingResult result,
                                    HttpSession session, @RequestParam("productId") int productId,
                                    @RequestParam("quantity") int quantity) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("cart.html");
            mav.addObject("bindingResult", result);
            return mav;
        }

        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        Product product = productRepository.findById(productId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Cart existingCartItem = cartService.getCartItemByUserAndProduct(user, product);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            existingCartItem.setSub_total(existingCartItem.getQuantity() * product.getPrice());
            cartService.updateCartItem(existingCartItem);
        } else {
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setSub_total(product.getPrice() * quantity);
            cartService.addItem(cartItem);
        }

        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/remove/{itemId}")
    public ModelAndView removeItem(@PathVariable int itemId) {
        cartService.removeItem(itemId);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/all")
    public List<Cart> getAllItems() {
        return cartService.getAllItems();
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getItemsByUserId(@PathVariable int userId) {
        return cartService.getItemsByUserId(userId);
    }

    @GetMapping("/user")
    public List<Cart> getItemsByUser(HttpSession session) {
        long userId = (long) session.getAttribute("id");
        return cartService.getItemsByUserId(userId);
    }
}
