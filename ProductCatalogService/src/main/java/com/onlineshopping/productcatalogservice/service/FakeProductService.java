package com.onlineshopping.productcatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FakeStoreProductService")
public class FakeProductService implements ProductService {


    private final RestTemplateBuilder restTemplateBuilder;

    private String getProdUrl="https://fakestoreapi.com/products/1";

    @Autowired
    public FakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<String> entity = restTemplate.getForEntity(getProdUrl, String.class);
        return "Product Service from Fake Store "+entity.toString();
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
