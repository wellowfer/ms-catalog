package br.com.fiap.catalog.infrastructure.controllers.DTO.product;

import br.com.fiap.catalog.main.Enum.CategoryEnum;

public record CategoryDetailsResponse(
    int id,
    String name,
    String key) {
        
    public CategoryDetailsResponse(CategoryEnum categoryEnum) {
        this(categoryEnum.getSequence(), categoryEnum.getDescription(), categoryEnum.name());
    }
}
