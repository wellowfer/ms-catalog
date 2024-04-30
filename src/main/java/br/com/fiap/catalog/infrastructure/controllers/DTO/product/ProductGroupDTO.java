package br.com.fiap.catalog.infrastructure.controllers.DTO.product;

import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.util.List;

public record ProductGroupDTO(
        CategoryEnum category,
        List<ProductResponse> products
) { }
