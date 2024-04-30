package br.com.fiap.catalog.infrastructure.controllers.mappers;

import br.com.fiap.catalog.domain.entity.Product;
import br.com.fiap.catalog.infrastructure.controllers.DTO.product.*;
import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDTOMapper {
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.id(), product.name(), product.description(), product.price(), product.category(), product.createdAt(), product.updatedAt());
    }

    public Product toProduct(CreateProductRequest request, CategoryEnum category) {
        return new Product(null, request.name(), request.description(), request.price(), category, null, null);
    }

    public Product toUpdaProduct(UpdateProductRequest request, CategoryEnum category) {
        return new Product(request.id(), request.name(), request.description(), request.price(), category, null, null);
    }

    public List<ProductResponse> toProductsResponse(List<Product> products) {
        return products.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<ProductGroupDTO> mapProductsByCategory(List<Product> products) {
        Map<CategoryEnum, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category));

        return groupedByCategory.entrySet().stream()
                .map(entry -> {
                    CategoryEnum category = entry.getKey();
                    List<ProductResponse> sortedProducts = entry.getValue().stream()
                            .sorted(Comparator.comparing(Product::name))
                            .map(this::toResponse)
                            .collect(Collectors.toList());

                    return new ProductGroupDTO(category, sortedProducts);
                })
                .collect(Collectors.toList());
    }

    public List<CategoryDetailsResponse> categoryToResponse() {
        return Arrays.stream(CategoryEnum.values())
                        .map(CategoryDetailsResponse::new)
                        .collect(Collectors.toList());
    }
    
}
