package com.onlineshopping.productcatalogservice.service;

import java.util.List;

public interface ProductService {
    String getProductById(Long id);

    List<String> getProductAllProducts();

    String deleteProductById(Long id);

    String addProduct();

    String addProduct(Long prodId);
}
