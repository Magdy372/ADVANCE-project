package com.adv.adv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adv.adv.model.*;
import com.adv.adv.repository.*;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrder(User user) {
        List<Cart> cartItems = cartService.getItemsByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cannot place an order with an empty cart");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order = orderRepository.save(order);

        for (Cart cartItem : cartItems) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getSub_total());
            orderDetailRepository.save(orderDetail);
        }

        cartService.clearCart(user.getId());

        return order;
    }
}

