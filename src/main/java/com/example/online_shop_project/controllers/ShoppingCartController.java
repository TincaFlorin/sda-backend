package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.ShoppingCart;
import com.example.online_shop_project.repositories.ShoppingCartRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;

@CrossOrigin
@RequestMapping("/shopping-cart")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @GetMapping("/list")
    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartRepository.findAll();
    }

    @PostMapping("/add")
    public String addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        return "Success";
    }

    @DeleteMapping("/delete/{shoppingCartId}")
    public String deleteShoppingCart(@PathVariable(name = "shoppingCartId") Integer shoppingCartId) {
        shoppingCartRepository.deleteById(shoppingCartId);
        return "Success";
    }
}
