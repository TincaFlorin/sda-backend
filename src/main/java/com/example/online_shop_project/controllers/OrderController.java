package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Order;
import com.example.online_shop_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

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
        orderRepository.save(order);
        return "Success";
    }

}
