package com.onlineshopping.productcatalogservice.service;


import org.springframework.stereotype.Service;

import java.util.List;
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{
    @Override
    public String getProductById(Long id) {
        return "Product from Service Class "+id.toString();
    }

    @Override
    public List<String> getProductAllProducts() {
        return null;
    }

    @Override
    public String deleteProductById(Long id) {
        return null;
    }

    @Override
    public String addProduct() {
        return null;
    }

    @Override
    public String addProduct(Long prodId) {
        return null;
    }
}


