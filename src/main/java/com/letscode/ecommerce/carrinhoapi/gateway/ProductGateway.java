package com.letscode.ecommerce.carrinhoapi.gateway;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductGateway {

    private final RestTemplate restTemplate;

    public ProductGateway(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> getProduct(Long productId) {
        return restTemplate.getForEntity("http://produtos-mysql-app-intance:8081/v1/api/product/{productId}", String.class, productId);
    }


}
