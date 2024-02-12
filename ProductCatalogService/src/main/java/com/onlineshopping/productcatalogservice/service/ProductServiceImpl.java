package com.onlineshopping.productcatalogservice.service;


import com.onlineshopping.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public String deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return product;
    }

}


