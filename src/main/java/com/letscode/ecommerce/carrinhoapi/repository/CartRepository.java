package com.letscode.ecommerce.carrinhoapi.repository;

import com.letscode.ecommerce.carrinhoapi.domain.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends MongoRepository<CartEntity, String> {

}
