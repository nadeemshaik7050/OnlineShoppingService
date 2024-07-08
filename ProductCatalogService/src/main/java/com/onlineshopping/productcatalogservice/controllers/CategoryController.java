package com.onlineshopping.productcatalogservice.controllers;

import com.onlineshopping.productcatalogservice.Exceptions.CategoryNotFoundException;
import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.service.CategoryService;
import com.onlineshopping.productcatalogservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private CategoryService categoryService;
    private ProductService productService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/allcategories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public Category deleteCategoryById(@PathVariable Long id) {
        return  categoryService.deleteCategoryById(id);
    }

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category) {
        return  categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,@RequestBody Category category) throws CategoryNotFoundException {
        return categoryService.updateCategory(id,category);
    }
    
}
