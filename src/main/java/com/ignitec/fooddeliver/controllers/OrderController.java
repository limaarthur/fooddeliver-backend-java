package com.ignitec.fooddeliver.controllers;

import com.ignitec.fooddeliver.dtos.OrderDTO;
import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.services.OrderService;
import com.ignitec.fooddeliver.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderListDTO = orderService.findAll();
        return orderListDTO;
    }
}
