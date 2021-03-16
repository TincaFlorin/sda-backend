package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Order;
import com.example.online_shop_project.repositories.OrderRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/api/order")
@RestController
public class OrderController extends BaseController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/list")
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId) {
        return orderRepository.findById(orderId).get();
    }

    @PostMapping("/add")
    public String addOrder(@RequestBody Order order) {
        Optional<User> currentUser = getLoggedInUser();
        com.example.online_shop_project.entitites.User user = userRepository.findByUsername(currentUser.get().getUsername());
        order.getShoppingCart().addAll(user.getItems());
        orderRepository.save(order);
        return "Success";
    }

}
