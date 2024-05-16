package com.adv.adv.controller;

import com.adv.adv.model.Cart;
import com.adv.adv.model.Checkout;
import com.adv.adv.model.User;
import com.adv.adv.repository.CartRepository;
import com.adv.adv.repository.CheckoutRepository;
import com.adv.adv.repository.userRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @GetMapping({"/", ""})
    public ModelAndView checkoutForm(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        Checkout checkout = new Checkout();
        
        // Retrieve all active carts for the user
        List<Cart> activeCarts = cartRepository.findByUserId(userId);

        if (activeCarts.isEmpty()) {
            return new ModelAndView("error").addObject("message", "No active carts found for user.");
        }

        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("checkout", checkout);
        mav.addObject("carts", activeCarts);

        return mav;
    }
    @PostMapping("/purchase")
    public ModelAndView purchase(@Valid @ModelAttribute("checkout") Checkout checkout,
                                 BindingResult result,
                                 HttpSession session) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("checkout");
            mav.addObject("bindingResult", result);
            mav.addObject("checkout", checkout); // Ensure checkout object is added to the model
            return mav;
        }

        Long userId = (Long) session.getAttribute("id");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<Cart> activeCarts = cartRepository.findByUserId(userId);

        if (activeCarts.isEmpty()) {
            return new ModelAndView("error").addObject("message", "No active carts found for user.");
        }

        // Calculate the total subtotal
        double totalSubTotal = activeCarts.stream()
                                          .mapToDouble(Cart::getSub_total)
                                          .sum();
        checkout.setSub_total(totalSubTotal);

        checkout.setUser(user);
        checkout.setItems(activeCarts);

        for (Cart cart : activeCarts) {
            cart.setCheckout(checkout);
        }

        checkoutRepository.save(checkout);

        return new ModelAndView("redirect:/");
    }
    
}
    