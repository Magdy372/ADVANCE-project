package com.adv.adv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.adv.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    Cart deleteById(int itemId);
    Cart findById(int id);
    List<Cart> findByUserId(long userId);
    Cart findByUserIdAndProductId(Long userId, int productId);
    
}
