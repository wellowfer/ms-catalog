package br.com.fiap.catalog.domain.entity;

import br.com.fiap.catalog.main.Enum.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(
    Long id,
    String name,
    String description,
    BigDecimal price,
    CategoryEnum category,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) { }