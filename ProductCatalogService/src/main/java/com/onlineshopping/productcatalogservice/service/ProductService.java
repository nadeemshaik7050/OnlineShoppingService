package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product deleteProductById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    Page<Product> getAllProducts(int pageNum, int size,String sortBy,String sortOrder);

}
