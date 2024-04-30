package br.com.fiap.catalog.main;

import br.com.fiap.catalog.application.gateways.ProductGateway;
import br.com.fiap.catalog.application.usecase.ProductInteractor;
import br.com.fiap.catalog.infrastructure.controllers.mappers.ProductDTOMapper;
import br.com.fiap.catalog.infrastructure.persistences.gateways.ProductRepositoryGateway;
import br.com.fiap.catalog.infrastructure.persistences.gateways.mapper.ProductEntityMapper;
import br.com.fiap.catalog.infrastructure.persistences.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    ProductInteractor productInteractor(ProductGateway productGateway) {
        return new ProductInteractor(productGateway);
    }

    @Bean
    ProductGateway productGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        return new ProductRepositoryGateway(productRepository, productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapper();
    }
}
