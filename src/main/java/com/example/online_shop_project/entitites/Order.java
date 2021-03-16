package com.example.online_shop_project.entitites;

import com.example.online_shop_project.controllers.BaseController;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseController {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<ShoppingCartItem> shoppingCart;


    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingCartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
