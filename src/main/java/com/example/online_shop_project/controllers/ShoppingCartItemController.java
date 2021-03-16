package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.ShoppingCartItem;
import com.example.online_shop_project.models.BasicAuthResponseModel;
import com.example.online_shop_project.repositories.ShoppingCartItemRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartItemController extends BaseController {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/items")
    public List<ShoppingCartItem> getShoppingCartItems() {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user = userRepository.findByUsername(currentUser.get().getUsername());
        return user.getItems();
    }

    @PostMapping("/add")
    public void addItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
        List<ShoppingCartItem> items = user.getItems();
            for(ShoppingCartItem item : items){
                if (item.getProduct().getProductName().equals(shoppingCartItem.getProduct().getProductName())) {
                    item.setQuantity(item.getQuantity() + 1);
                }
                else {
                    items.add(shoppingCartItem);
                }
            }

        shoppingCartItemRepository.save(shoppingCartItem);
    }

    @DeleteMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable(name = "itemId")Integer itemId) {
        shoppingCartItemRepository.deleteById(itemId);
        return "Success";
    }


}
