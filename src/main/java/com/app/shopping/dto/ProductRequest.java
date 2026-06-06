package com.app.shopping.dto;

import java.math.BigDecimal;

public record ProductRequest(
    String name,
    String brand,
    BigDecimal price,
    int inventory,
    String description,
    String category
) {}
