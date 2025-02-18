package com.ignitec.fooddeliver.controllers;

import com.ignitec.fooddeliver.dtos.OrderDTO;
import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.services.OrderService;
import com.ignitec.fooddeliver.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<OrderDTO> insertOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.inserOrder(orderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.setDelivered(id);
        return ResponseEntity.ok().body(orderDTO);
    }
}
