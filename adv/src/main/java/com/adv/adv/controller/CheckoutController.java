// package com.adv.adv.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.adv.adv.repository.CartRepository;
// import com.adv.adv.repository.CheckoutRepository;

// @RestController
// @RequestMapping("/checkout")
// public class CheckoutController {
//     private final CartRepository cartRepository;
//     private final CheckoutRepository checkoutRepository;
    
//     @Autowired
//     public CheckoutController(CartRepository cartRepository, CheckoutRepository checkoutRepository) {
//     this.cartRepository = cartRepository;
//     this.checkoutRepository = checkoutRepository;
// }
//     @PostMapping("/purchase")
//     public String purchase(@RequestBody Checkout checkout) {
//     // Save the checkout details
//     checkoutRepository.save(checkout);

//     // Clear the cart after purchase
//     Cart cart = checkout.getCart();
//     cart.getItems().clear();
//     cartRepository.save(cart);

//     return "Purchase successful for checkout ID: " + checkout.getId();
// }
// }


