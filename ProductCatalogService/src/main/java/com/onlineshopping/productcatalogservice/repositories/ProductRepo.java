package com.onlineshopping.productcatalogservice.repositories;

import com.onlineshopping.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long uuid);
}
