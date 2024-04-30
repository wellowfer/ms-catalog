package br.com.fiap.catalog.application.usecase;

import br.com.fiap.catalog.application.gateways.ProductGateway;
import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.exception.CatalogException;
import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.util.List;

public class ProductInteractor {
    private ProductGateway gateway;

    public ProductInteractor(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public Product createProduct(Product productBusniessObj) {
        return gateway.saveProduct(productBusniessObj);
    }

    public Product updateProduct(Product productBusniessObj) throws CatalogException {
        Product product = gateway.findById(productBusniessObj.id()); 
        Product productClient = new Product(product.id(), productBusniessObj.name(), productBusniessObj.description(), productBusniessObj.price(), productBusniessObj.category(), null, null);
        return gateway.saveProduct(productClient);
    }

    public Product findProductById(Long id) throws CatalogException {
        return gateway.findById(id); 
    }

    public List<Product> findProductsByCategory(CategoryEnum category) throws CatalogException {
        return gateway.findByCategory(category);
    }

    public void deleteProduct(Long id) throws CatalogException {
        Product product = gateway.findById(id); 
        gateway.deleteProduct(product);
    }

    public List<Product> findProductsByIds(List<Long> ids) throws CatalogException {
        List<Product> products = gateway.findByIds(ids);
        if (products.isEmpty()) {
            throw new CatalogException("Fail do find id's from order list!");
        }
        return products;
    }

    public List<Product> findAll() {
        return gateway.findAll();
    }
}
