package com.adv.adv.repository;
import com.adv.adv.model.User;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adv.adv.model.Cart;
import com.adv.adv.model.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    Cart deleteById(int itemId);
    <Optional>Cart findById(Long cartId);
    List<Cart> findByUserId(long userId);
    Cart findByUserIdAndProductId(Long userId, int productId);
    Cart findByUserAndProduct(User user, Product product);
    @Transactional
    void deleteByUserId(Long userId);
}
