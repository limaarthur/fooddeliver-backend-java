package com.ignitec.fooddeliver.repositories;

import com.ignitec.fooddeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
