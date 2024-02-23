package com.onlineshopping.productcatalogservice.service;


import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.repositories.CategoryRepo;
import com.onlineshopping.productcatalogservice.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product=productRepo.findById(id);
        if(!product.isPresent()){
            throw new ProductNotFoundException("Product not found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product deleteProductById(Long id) {
        Product product=productRepo.findById(id).get();
        Category category=product.getCategory();
        productRepo.delete(product);
        categoryRepo.deleteById(category.getId());
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> category =categoryRepo.findByTitle(product.getCategory().getTitle());
        if(!category.isPresent()){
            Category newCategory=new Category();
            newCategory.setTitle(product.getCategory().getTitle());
            categoryRepo.save(newCategory);
        }
        Product savedProd=productRepo.save(product);
        return savedProd;
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

}


