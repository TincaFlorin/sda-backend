package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.entitites.ShoppingCartItem;
import com.example.online_shop_project.repositories.ProductRepository;
import com.example.online_shop_project.repositories.ShoppingCartItemRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cart")
public class ShoppingCartItemController extends BaseController {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/items")
    public List<ShoppingCartItem> getShoppingCartItems() {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user = userRepository.findByUsername(currentUser.get().getUsername());
        return user.getItems();
    }

    @PostMapping("/add")
    public void addItem(@RequestBody Product product) {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
//
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setQuantity(1);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setUser(user);
        shoppingCartItemRepository.save(shoppingCartItem);
    }

    @PostMapping("/increment")
    public void incrementItem(@RequestBody Product product) {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
        List<ShoppingCartItem> items = user.getItems();
        for(ShoppingCartItem item : items) {
            if(item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity()+ 1);
                shoppingCartItemRepository.save(item);
                return;
            }
        }
    }

    @PostMapping("/decrement")
    public void decrementItem(@RequestBody Product product) {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
        List<ShoppingCartItem> items = user.getItems();
        for(ShoppingCartItem item : items) {
            if(item.getProduct().getId() == product.getId()) {
                if(item.getQuantity() - 1 == 0){
                    shoppingCartItemRepository.delete(item);
                }
                else {
                    item.setQuantity(item.getQuantity() - 1);
                    shoppingCartItemRepository.save(item);
                }
            }
        }
    }

    @DeleteMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable(name = "itemId")Integer itemId) {
        shoppingCartItemRepository.deleteById(itemId);
        return "Success";
    }

    @GetMapping("/get-by-product/{id}")
    public ShoppingCartItem getShoppingCartItemByProduct(@PathVariable(name = "id") Integer id) {
        Product product = productRepository.findById(id).get();
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
        List<ShoppingCartItem> userItems = shoppingCartItemRepository.findByUser(user);
            for(ShoppingCartItem item : userItems) {
                if(item.getProduct().getProductName().equals(product.getProductName()))
                    return item;
            }
        return null;
    }

    @GetMapping("/total-products")
    public Integer getTotalShoppingCartItems() {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user
                = userRepository.findByUsername(currentUser.get().getUsername());
        List<ShoppingCartItem> items = shoppingCartItemRepository.findByUser(user);
        Integer totalProducts = 0;
        for(ShoppingCartItem item : items) {
            totalProducts += item.getQuantity();
        }
        return totalProducts;
    }
}
