package com.example.online_shop_project.models;

public class ProductDTO {

    private Integer id;

    private String category;

    private String productName;

    private String imageUrl;

    private Double price;

    public ProductDTO(String category, String productName, String imageUrl, Double price) {
        this.category = category;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
