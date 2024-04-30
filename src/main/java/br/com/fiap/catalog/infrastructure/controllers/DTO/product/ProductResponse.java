package br.com.fiap.catalog.infrastructure.controllers.DTO.product;

import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    CategoryEnum category,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) { }
