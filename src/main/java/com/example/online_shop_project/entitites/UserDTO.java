package com.example.online_shop_project.entitites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private ShoppingCart shoppingCart;
    private List<Order> orderList;
}
