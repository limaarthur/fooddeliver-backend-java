package com.ignitec.fooddeliver.repositories;

import com.ignitec.fooddeliver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
