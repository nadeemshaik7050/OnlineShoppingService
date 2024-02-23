package com.onlineshopping.productcatalogservice.repositories;

import com.onlineshopping.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long>{
    Optional<Category> findByTitle(String title);
}
