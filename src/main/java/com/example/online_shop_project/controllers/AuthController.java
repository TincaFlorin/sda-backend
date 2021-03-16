package com.example.online_shop_project.controllers;

import com.example.online_shop_project.entitites.Authority;
import com.example.online_shop_project.entitites.User;
import com.example.online_shop_project.models.BasicAuthResponseModel;
import com.example.online_shop_project.models.BasicUsernameTakenResponse;
import com.example.online_shop_project.repositories.AuthorityRepository;
import com.example.online_shop_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController extends BaseController{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;



    @GetMapping("/login")
    public BasicAuthResponseModel confrirmBasicAuthLoginMessage() {
        Optional<org.springframework.security.core.userdetails.User> currenUser = getLoggedInUser();
        User user = userRepository.findByUsername(currenUser.get().getUsername());
        List<String> authorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            authorities.add(authority.getUserAuthority());
        }
        return new BasicAuthResponseModel(authorities);
    }

    @PostMapping("/user/add")
    public void registerUser(@RequestBody User user) throws Exception {
        List<User> userList = userRepository.findAll();
        for(User databaseUser: userList) {
            if(user.getUsername().equals(databaseUser.getUsername())) {
                throw new Exception("Username already taken");
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user = userRepository.save(user);
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setUserAuthority("USER");
        authority.setUser(user);
        authorityRepository.save(authority);
    }
}