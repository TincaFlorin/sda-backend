package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.User;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable(name="userId") Integer userId) {
        return userRepository.findById(userId).get();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "Success";
    }

    @PutMapping("/update/{userId}")
    public String updateUser(@PathVariable(name = "userId")Integer userId, @RequestBody User user) {
        User updatedUser = userRepository.findById(userId).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
        return "Success";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable(name="userId")Integer userId) {
        userRepository.deleteById(userId);
        return "Success";
    }


}
