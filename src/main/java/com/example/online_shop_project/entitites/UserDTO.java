package com.example.online_shop_project.entitites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String username;
    private List<ShoppingCartItem> shoppingCart;
    private List<Order> orderList;
}
