package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.ShoppingCartItem;
import com.example.online_shop_project.repositories.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/item")
public class ShoppingCartItemController {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @PostMapping("/add")
    public String addItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        shoppingCartItemRepository.save(shoppingCartItem);
        return "Success";
    }

    @DeleteMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable(name = "itemId")Integer itemId) {
        shoppingCartItemRepository.deleteById(itemId);
        return "Success";
    }

}
