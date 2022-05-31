package com.letscode.ecommerce.carrinhoapi.config;

import com.letscode.ecommerce.carrinhoapi.domain.ItemsEntity;
import com.letscode.ecommerce.carrinhoapi.domain.ProductEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Bean
    @Scope("prototype")
    public ItemsEntity productEntity() {
        return new ItemsEntity();
    }
}
