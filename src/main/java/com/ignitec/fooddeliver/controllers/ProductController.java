package com.ignitec.fooddeliver.controllers;

import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        List<ProductDTO> productListDTO = productService.findAll();
        return productListDTO;
    }
}
