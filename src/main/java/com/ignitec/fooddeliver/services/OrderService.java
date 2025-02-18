package com.ignitec.fooddeliver.services;

import com.ignitec.fooddeliver.dtos.OrderDTO;
import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.entities.Order;
import com.ignitec.fooddeliver.entities.Product;
import com.ignitec.fooddeliver.repositories.OrderRepository;
import com.ignitec.fooddeliver.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findOrdersWithProducts();
        List<OrderDTO> ordertListDTO = orderList.stream().map(order -> new OrderDTO(order)).toList();
        return ordertListDTO;
    }
}
