package com.letscode.ecommerce.carrinhoapi.domain;

import org.springframework.http.ResponseEntity;

public class ProductEntity {

    private String name;
    private String description;
    private Double price;
    private String category;
    private String image;
    private Integer amount;

    public ProductEntity(String name, String description, Double price, String category, String image, Integer amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.amount = amount;
    }

    public ProductEntity(ResponseEntity<ProductEntity> productObj) {
        this.name = productObj.getBody().getName();
        this.description = productObj.getBody().getDescription();
        this.price = productObj.getBody().getPrice();
        this.category = productObj.getBody().getCategory();
        this.image = productObj.getBody().getImage();
        this.amount = productObj.getBody().getAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
