package com.example.online_shop_project.repositories;

import com.example.online_shop_project.entitites.Product;
import com.example.online_shop_project.entitites.ShoppingCartItem;
import com.example.online_shop_project.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

    List<ShoppingCartItem> findByUser(User user);

    ShoppingCartItem findByProduct(Product product);

}
