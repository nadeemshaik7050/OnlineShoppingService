package com.onlineshopping.productcatalogservice.controllers;


import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.ExceptionDto;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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

    @GetMapping("/allproducts")
    public List<Product> getProductAllProducts() {
        List<Product> list = productService.getAllProducts();
        return list;
    }

    @GetMapping("/")
    public Page<Product> getProductAllProductsWithPaginationandSorting(@RequestParam int pageNum, @RequestParam int size, @RequestParam String sortBy,@RequestParam String sortOrder) {
        Page<Product> page = productService.getAllProducts(pageNum, size, sortBy,sortOrder);
        return page;
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
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product) {
        return productService.updateProduct(id,product);
    }




}
