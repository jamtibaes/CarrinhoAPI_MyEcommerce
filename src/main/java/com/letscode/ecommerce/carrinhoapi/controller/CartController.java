package com.letscode.ecommerce.carrinhoapi.controller;

import com.letscode.ecommerce.carrinhoapi.domain.CartEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ItemsEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ProductEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ProductToCartRequest;
import com.letscode.ecommerce.carrinhoapi.gateway.ProductGateway;
import com.letscode.ecommerce.carrinhoapi.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("v1/api")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductGateway productGateway;

    public CartController(CartRepository cartRepository, ProductGateway productGateway) {
        this.cartRepository = cartRepository;
        this.productGateway = productGateway;
    }

    @GetMapping("/carts")
    public ResponseEntity<Iterable<CartEntity>> getAllCarts() {
        return ResponseEntity.ok(cartRepository.findAll());
    }

    @PostMapping("/cart")
    public ResponseEntity<CartEntity> createCart() {
        CartEntity cartEntity = new CartEntity();
        CartEntity cart = cartRepository.save(cartEntity);
        return ResponseEntity
                .created(URI.create(String.format("/v1/api/cart/%s", cart.getId())))
                .body(cart);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartEntity> getCart(@PathVariable String id) {
        Optional<CartEntity> cart = cartRepository.findById(id);

        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cart.get());
    }

    @PutMapping("/cart")
    public ResponseEntity<CartEntity> addProductCart(@RequestBody ProductToCartRequest productToCartRequest) {
        Optional<CartEntity> cart = cartRepository.findById(productToCartRequest.getCartId());


        if (cart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        //ResponseEntity<String> product = productGateway.getProduct(productToCartRequest.getProductId());
        ResponseEntity<ProductEntity> productObj = productGateway.getProductObj(productToCartRequest.getProductId());

        if (!productObj.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if (cart.get().getProductList().isEmpty()) {
            ItemsEntity itemsEntity1 = new ItemsEntity();
            itemsEntity1.setProductId(productToCartRequest.getProductId());
            itemsEntity1.setQuantity(productToCartRequest.getQuantity());
            itemsEntity1.setPrice(productObj.getBody().getPrice() * productToCartRequest.getQuantity());
            cart.get().getProductList().add(itemsEntity1);
        } else {
            cart.get().getProductList().stream()
                    .forEach(itemsEntity -> {
                        if(itemsEntity.getProductId().equals(productToCartRequest.getProductId())) {
                            itemsEntity.setQuantity(productToCartRequest.getQuantity());
                            itemsEntity.setPrice(productObj.getBody().getPrice() * productToCartRequest.getQuantity());
                        } else {
                            ItemsEntity entity = new ItemsEntity();
                            entity.setProductId(productToCartRequest.getProductId());
                            entity.setQuantity(productToCartRequest.getQuantity());
                            entity.setPrice(productObj.getBody().getPrice() * productToCartRequest.getQuantity());
                            cart.get().getProductList().add(entity);
                        }
                    });
        }
        cartRepository.save(cart.get());

        return ResponseEntity.ok(cart.get());
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<CartEntity> deleteCart(@PathVariable String id) {
        Optional<CartEntity> cart = cartRepository.findById(id);

        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        cartRepository.delete(cart.get());

        return ResponseEntity.noContent().build();
    }

}
