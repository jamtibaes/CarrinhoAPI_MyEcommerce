package com.letscode.ecommerce.carrinhoapi.domain;

public class ProductToCartRequest {

    private Long productId;
    private String cartId;
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public String getCartId() {
        return cartId;
    }

    public Integer getQuantity() {
        return quantity;
    }


}
