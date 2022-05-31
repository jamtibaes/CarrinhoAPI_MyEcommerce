package com.letscode.ecommerce.carrinhoapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "cart")
public class CartEntity {

    @Id
    private String id;

    @Field
    private List<ItemsEntity> productList = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemsEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ItemsEntity> productList) {
        this.productList = productList;
    }

}
