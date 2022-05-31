package com.letscode.ecommerce.carrinhoapi.domain;

public class ItemsEntity {

    private Long productId;
    private Integer quantity;
    private Double price;

    public ItemsEntity() {
    }

    public ItemsEntity(Long productId, Integer quantity,Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
