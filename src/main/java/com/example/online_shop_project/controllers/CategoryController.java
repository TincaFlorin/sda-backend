package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Category;
import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/list")
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @GetMapping("/products/{categoryId}")
    public List<Product> getProductList(@PathVariable(name = "categoryId") Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return category.getProducts();
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return "Success";
    }

    @PutMapping("/update/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId,@RequestBody Category category) {
        Category updatedCategory = categoryRepository.findById(categoryId).get();
        updatedCategory.setName(category.getName());
        categoryRepository.save(updatedCategory);
        return "Success";
    }

}
