package com.onlineshopping.productcatalogservice.controllers;


import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.ExceptionDto;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * 1.GetProdById
 * 2.GetProductLists
 * 3.AddProduct
 * 4.DeleteProduct
 * 5.updateProduct
 *
 * */

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<Product> getProductAllProducts() {
        List<Product> list = productService.getAllProducts();
        return list;
    }

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable Long id) {
        return  productService.deleteProductById(id);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return  productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long prodId) {
        return "prod updated";
    }




}
