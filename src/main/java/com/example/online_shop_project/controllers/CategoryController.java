package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Category;
import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }


    @GetMapping("/{categoryId}")
    public Category getCategoryList(@PathVariable(name = "categoryId") Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @GetMapping("/products/{categoryId}")
    public List<Product> getProductList(@PathVariable(name = "categoryId") Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return category.getProducts();
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
    }

    @PutMapping("/update/{categoryId}")
    public void updateCategory(@PathVariable(name = "categoryId") Integer categoryId, @RequestBody Category category) {
        category.setId(categoryId);
        categoryRepository.save(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
