package com.letscode.ecommerce.carrinhoapi.controller;

import com.letscode.ecommerce.carrinhoapi.domain.CartEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ItemsEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ProductToCartRequest;
import com.letscode.ecommerce.carrinhoapi.gateway.ProductGateway;
import com.letscode.ecommerce.carrinhoapi.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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
    public ResponseEntity<CartEntity> createCart(@RequestBody CartEntity cartEntity) {
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

        ResponseEntity<String> product = productGateway.getProduct(productToCartRequest.getProductId());

        if (!product.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        cart.get().getProductList().stream()
                        .forEach(itemsEntity -> {
                            if(itemsEntity.getProductId().equals(productToCartRequest.getProductId())) {
                                itemsEntity.setQuantity(productToCartRequest.getQuantity());
                            }
                        });

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
