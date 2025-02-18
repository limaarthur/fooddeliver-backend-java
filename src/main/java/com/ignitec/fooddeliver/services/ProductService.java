package com.ignitec.fooddeliver.services;

import com.ignitec.fooddeliver.dtos.ProductDTO;
import com.ignitec.fooddeliver.entities.Product;
import com.ignitec.fooddeliver.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAllByOrderByNameAsc();
        List<ProductDTO> productListDTO = productList.stream().map(product -> new ProductDTO(product)).toList();
        return productListDTO;
    }
}
