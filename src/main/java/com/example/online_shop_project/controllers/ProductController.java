package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/list")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path="/{productId}")
    public Product getProduct(@PathVariable(name = "productId") Integer productId) {
        return productRepository.findById(productId).get();
    }

    @PostMapping(path = "/add")
    public String addProduct(@RequestBody Product product) {
        this.productRepository.save(product);
        return "Success";
    }

    @PutMapping(path = "/update/{productId}")
    public String updateProduct(@PathVariable(name = "productId")Integer productId, @RequestBody Product product) {
        Product updatedProduct = productRepository.findById(productId).get();
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        productRepository.save(updatedProduct);
        return "Success";
    }

    @DeleteMapping(path ="/delete/{productId}")
    public String deleteProduct(@PathVariable(name="productId") Integer productId) {
        productRepository.deleteById(productId);
        return "Success";
    }

}
