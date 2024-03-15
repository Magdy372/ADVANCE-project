package com.adv.adv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.adv.adv.model.Wishlist;
import com.adv.adv.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistItemRepository;

    @SuppressWarnings("null")
    public Wishlist addItem(Wishlist item) {
        wishlistItemRepository.save(item);
        return item; 
    }

    public void removeItem(int itemId) {
        wishlistItemRepository.deleteById(itemId);
    }

    public List<Wishlist> getAllItems() {
        return wishlistItemRepository.findAll();
    }

    public List<Wishlist> getItemsByUserId(long userId) {
        return wishlistItemRepository.findByUserId(userId);
    }
    public boolean doesItemExistForUser(Long userId, int productId) {
        Wishlist wishlistItem = wishlistItemRepository.findByUserIdAndProductId(userId, productId);
        return wishlistItem != null;
    }
}
