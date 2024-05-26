package com.adv.adv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.adv.model.Cart;
import com.adv.adv.model.Product;
import com.adv.adv.model.User;
import com.adv.adv.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartItemRepository;

    public Cart addItem(Cart item) {
        cartItemRepository.save(item);
        return item; 
    }

    public void removeItem(int itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public List<Cart> getAllItems() {
        return cartItemRepository.findAll();
    }

    public List<Cart> getItemsByUserId(long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public Cart getCartItemByUserAndProduct(User user, Product product) {
        return cartItemRepository.findByUserAndProduct(user, product);
    }

    public void updateCartItem(Cart cartItem) {
        cartItemRepository.save(cartItem);
    }

    public boolean doesItemExistForUser(Long userId, int productId) {
        Cart cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        return cartItem != null;
    }

    public double calculateTotalPrice(List<Cart> cartItems) {
        double totalPrice = 0.0;
        for (Cart item : cartItems) {
            totalPrice += item.getSub_total();
        }
        return totalPrice;
    }

    public void clearCart(Long userId) {
      cartItemRepository.deleteByUserId(userId);
  }
}
