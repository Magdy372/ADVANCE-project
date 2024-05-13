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
  private CartService cartService;

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
        
        // Use the retrieved user ID to get cart items
        List<Cart> cartItems = cartService.getItemsByUserId(userId);
         // Calculate total price
        double totalPrice = cartService.calculateTotalPrice(cartItems);
        // Create a new ModelAndView object
        ModelAndView mav = new ModelAndView("cart.html");
        
        // Add the cart items to the ModelAndView object
        mav.addObject("cartItems",cartItems);
        mav.addObject("totalPrice", totalPrice);

        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addItem(@Valid @ModelAttribute("cart") Cart cartItem,
                            BindingResult result,
                            HttpSession session,
                            @RequestParam("productId") int productId) {

    if (result.hasErrors()) {
        ModelAndView mav = new ModelAndView("cart.html");
        // Add necessary model attributes if needed
        mav.addObject("bindingResult", result);
        return mav; // Return ModelAndView directly
    }

    Long userId = (Long) session.getAttribute("id");
    if (userId == null) {
        return new ModelAndView("redirect:/login");
    }
    
    // Retrieve the product by its ID
    Product product = productRepository.findById(productId);
    User user = userRepository.findById((long) userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    // Create a new cart item
    // Set the User and Product for the cart item
    cartItem.setUser(user);
    cartItem.setProduct(product);
    cartItem.setProductPrice(1);
    
    // Calculate sub-total based on product price and quantity
    // double subTotal = product.getPrice() * quantity;
    // cartItem.setSub_total(subTotal);
    

    // Add the cart item
    Cart savedItem = cartService.addItem(cartItem);
    
    return new ModelAndView("redirect:/");
}

@PostMapping("/add")
public ModelAndView addQuantity(@Valid @ModelAttribute("cart") Cart cartItem,
                        BindingResult result,
                        HttpSession session, @RequestParam("productId") int productId,
                        @RequestParam("quantity") int quantity) {

if (result.hasErrors()) {
    ModelAndView mav = new ModelAndView("cart.html");
    // Add necessary model attributes if needed
    mav.addObject("bindingResult", result);
    return mav; // Return ModelAndView directly
}

Long userId = (Long) session.getAttribute("id");
if (userId == null) {
    return new ModelAndView("redirect:/login");
}

// Retrieve the product by its ID
Product product = productRepository.findById(productId);
User user = userRepository.findById((long) userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

// Create a new cart item
// Set the User and Product for the cart item
cartItem.setUser(user);
cartItem.setProduct(product);
cartItem.setProductPrice(1);

// Calculate sub-total based on product price and quantity
double subTotal = product.getPrice() * quantity;
cartItem.setSub_total(subTotal);
cartItem.setQuantity(cartItem.getQuantity());


Cart savedItem = cartService.addItem(cartItem);

return new ModelAndView("redirect:/");
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
        //return cartService.getItemsByUserId(userId);
        long userId =  (long) session.getAttribute("id");
        List<Cart> cartItems = cartService.getItemsByUserId(userId);
        return cartItems;
    }
    

}