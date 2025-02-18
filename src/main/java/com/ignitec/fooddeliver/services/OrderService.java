package com.ignitec.fooddeliver.services;

import com.ignitec.fooddeliver.dtos.OrderDTO;
import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.entities.Order;
import com.ignitec.fooddeliver.entities.OrderStatus;
import com.ignitec.fooddeliver.entities.Product;
import com.ignitec.fooddeliver.repositories.OrderRepository;
import com.ignitec.fooddeliver.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findOrdersWithProducts();
        List<OrderDTO> ordertListDTO = orderList.stream().map(order -> new OrderDTO(order)).toList();
        return ordertListDTO;
    }

    @Transactional
    public OrderDTO inserOrder(OrderDTO orderDTO) {
        Order order = new Order(null,
                OrderStatus.PENDING, Instant.now(), orderDTO.getLongitude(),
                orderDTO.getLatitude(), orderDTO.getAddress());

        for (ProductDTO p : orderDTO.getProductsDTO()) {
            Optional<Product> product = productRepository.findById(p.getId());
            product.ifPresent(order.getProducts()::add);
        }

        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}
