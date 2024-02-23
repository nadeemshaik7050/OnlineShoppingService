package com.onlineshopping.productcatalogservice.repositories;

import com.onlineshopping.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long uuid);
}
