package br.com.fiap.catalog.application.gateways;

import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.util.List;

public interface ProductGateway {
    Product saveProduct(Product product);
    void deleteProduct(Product product);
    Product findById(Long id);
    List<Product> findByCategory(CategoryEnum category);
    List<Product> findByIds(List<Long> ids);
    List<Product> findAll();
}
