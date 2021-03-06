package com.example.online_shop_project.entitites;

import javax.persistence.*;

@Entity
@Table(name="shopping_cart_item")
public class ShoppingCartItem {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;

    @OneToOne
    @JoinColumn(name ="product_id", referencedColumnName = "id")
    private Product product;


    public ShoppingCartItem() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
