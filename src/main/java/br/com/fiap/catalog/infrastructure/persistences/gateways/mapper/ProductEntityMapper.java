package br.com.fiap.catalog.infrastructure.persistences.gateways.mapper;

import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.infrastructure.persistences.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ProductEntityMapper {

    public ProductEntity toEntity(Product productDomainObj) {
        return new ProductEntity(productDomainObj.id(), productDomainObj.name(), productDomainObj.description(), productDomainObj.price(), productDomainObj.category());
    }

    public Product toDomainObj(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getCategory(), productEntity.getCreatedAt(), productEntity.getUpdatedAt());
    }

    public List<Product> toDomainObjs(List<ProductEntity> productObjs) {
        return productObjs.stream().map(this::toDomainObj).collect(Collectors.toList());
    }
}
