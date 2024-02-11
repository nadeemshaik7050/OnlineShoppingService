package com.onlineshopping.productcatalogservice.controllers;


import com.onlineshopping.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public  String getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<String> getProductAllProducts(){
        List<String> list=new ArrayList<>();
        return list;
    }

    @DeleteMapping("/delete/{id}")
    public  String deleteProductById(@PathVariable Long id){
        System.out.println("prod deleted");
        return "prod deleted";
    }

    @PostMapping("/addProduct")
    public  String addProduct(){
        System.out.println("prod deleted");
        return "prod Added";
    }

    @PutMapping("/{id}")
    public  String addProduct(@PathVariable Long prodId){
        return "prod updated";
    }


}
