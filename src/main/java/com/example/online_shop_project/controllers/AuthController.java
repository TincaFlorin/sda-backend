package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Authority;
import com.example.online_shop_project.entitites.User;
import com.example.online_shop_project.repositories.AuthorityRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping("/login")
    public String confrirmBasicAuthLoginMessage() {
        return "Success";
    }

    @PostMapping("/user/add")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user = userRepository.save(user);
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setUserAuthority("USER");
        authority.setUser(user);
        authorityRepository.save(authority);
        return "Success";
    }
}