package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Authority;
import com.example.online_shop_project.entitites.User;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
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

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable(name="username") String username) {
        return userRepository.findByUsername(username);
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

    @GetMapping("/authorities")
    public List<Authority> getAuthorities() {
        Optional<org.springframework.security.core.userdetails.User> currentUser = getLoggedInUser();
        User user = userRepository.findByUsername(getLoggedInUser().get().getUsername());
        return user.getAuthorities();
    }

}
