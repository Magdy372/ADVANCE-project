package com.adv.adv.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.adv.model.Product;
import com.adv.adv.model.User;
import com.adv.adv.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    // You can add custom query methods if needed

    Wishlist deleteById(int itemId);
    Wishlist findById(int id);
    List<Wishlist> findByUserId(long userId);
    
    
}