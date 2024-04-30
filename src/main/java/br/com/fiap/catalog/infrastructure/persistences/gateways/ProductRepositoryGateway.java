package br.com.fiap.catalog.infrastructure.persistences.gateways;

import br.com.fiap.catalog.application.gateways.ProductGateway;
import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.exception.CatalogException;
import br.com.fiap.catalog.infrastructure.persistences.entity.ProductEntity;
import br.com.fiap.catalog.infrastructure.persistences.gateways.mapper.ProductEntityMapper;
import br.com.fiap.catalog.infrastructure.persistences.repository.ProductRepository;
import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.util.List;

@SuppressWarnings("null")
public class ProductRepositoryGateway implements ProductGateway {
    private final ProductRepository repository;
    private final ProductEntityMapper mapper;

    
    public ProductRepositoryGateway(ProductRepository repository, ProductEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product saveProduct(Product productDomainObj) {
        ProductEntity productEntity = mapper.toEntity(productDomainObj);
        ProductEntity savedObj = repository.save(productEntity);
        return mapper.toDomainObj(savedObj);
    }

    @Override
    public void deleteProduct(Product productDomainObj) {
        ProductEntity productEntity = mapper.toEntity(productDomainObj);
        repository.delete(productEntity);
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productObj = repository.findById(id).orElseThrow(() -> new CatalogException("Product not found with ID: " + id));
        return mapper.toDomainObj(productObj);
    }

    @Override
    public List<Product> findByCategory(CategoryEnum category) {
        List<ProductEntity> productObjs = repository.findByCategory(category);
        return mapper.toDomainObjs(productObjs);
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        List<ProductEntity> productObjs = repository.findAllByIdIn(ids);
        return mapper.toDomainObjs(productObjs);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productObjs = repository.findAllByOrderByCategoryAscNameAsc();
        return mapper.toDomainObjs(productObjs);
    }
}
