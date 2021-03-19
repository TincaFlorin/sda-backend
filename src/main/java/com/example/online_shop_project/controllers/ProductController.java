package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Category;
import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.models.ProductDTO;
import com.example.online_shop_project.repositories.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/list")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path="/{productId}")
    public ProductDTO getProduct(@PathVariable(name = "productId") Integer productId) {
        Product product =  productRepository.findById(productId).get();

        ProductDTO productDTO = new ProductDTO(
                product.getCategory().getName(),
                product.getProductName(),
                product.getImageUrl(),
                product.getPrice()
                );
        productDTO.setId(productId);
        return productDTO;
    }

    @PostMapping(path = "/add")
    public String addProduct(@RequestBody ProductDTO productDTO) {
        Category category = this.categoryRepository.findByName(productDTO.getCategory());

        Product product = new Product(
                category,
                productDTO.getProductName(),
                productDTO.getImageUrl(),
                productDTO.getPrice()
        );
        this.productRepository.save(product);
        return "Success";
    }

    @PutMapping(path = "/update/{productId}")
    public String updateProduct(@PathVariable(name = "productId")Integer productId,
                                @RequestBody ProductDTO productDTO) {
        Category category = this.categoryRepository.findByName(productDTO.getCategory());

        Product product = new Product();

        product.setId(productId);
        product.setCategory(category);
        product.setProductName(productDTO.getProductName());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());

        productRepository.save(product);

        return "Success";
    }

    @DeleteMapping(path ="/delete/{productId}")
    public String deleteProduct(@PathVariable(name="productId") Integer productId) {
        productRepository.deleteById(productId);
        return "Success";
    }

}
