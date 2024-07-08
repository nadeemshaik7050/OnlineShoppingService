package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.CategoryNotFoundException;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.repositories.CategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        Optional<Category> category=categoryRepo.findById(id);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category not found");
        }
        return category.get();
    }

    public List<Category> getAllCategories() {
        List<Category> list = categoryRepo.findAll();
        return list;
    }

    public Category deleteCategoryById(@PathVariable Long id) {
        Category category=categoryRepo.findById(id).get();
        categoryRepo.deleteById(id);
        return category;
    }

    public Category addCategory(@RequestBody Category category) {
        return  categoryRepo.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,@RequestBody Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional=categoryRepo.findById(id);
        if(categoryOptional.isPresent()){
            Category category1=categoryOptional.get();
            category1.setTitle(category.getTitle());
            categoryRepo.save(category1);
            return category1;
        }
        throw new CategoryNotFoundException("Category not found");
    }


}
