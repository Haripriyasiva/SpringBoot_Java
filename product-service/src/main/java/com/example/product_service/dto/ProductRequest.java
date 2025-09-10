package com.example.product_service.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        @Size(max = 500) String description,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull @Min(0) Integer stock
) {}
