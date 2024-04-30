package br.com.fiap.catalog.infrastructure.controllers.DTO.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank(message = "name: Campo obigatório.")
    String name,
    @NotBlank(message = "description: Campo obigatório.")
    String description,
    @DecimalMin(value = "0.0", inclusive = false, message = "price: Campo obrigatório e deve ser maior que 0.")
    BigDecimal price) { }
