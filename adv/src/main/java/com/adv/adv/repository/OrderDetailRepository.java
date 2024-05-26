package com.adv.adv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adv.adv.model.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
}
