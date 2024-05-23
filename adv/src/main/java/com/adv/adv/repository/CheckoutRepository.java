package com.adv.adv.repository;

import com.adv.adv.model.Checkout;
import com.adv.adv.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {


}
