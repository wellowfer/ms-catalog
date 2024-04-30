package br.com.fiap.catalog.infrastructure.controllers.DTO.product;

import java.math.BigDecimal;

public record UpdateProductRequest(
    Long id,
    String name,
    String description,
    BigDecimal price) { }
