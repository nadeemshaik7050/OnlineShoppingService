package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product deleteProductById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

}
