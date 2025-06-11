package com.example.usedCarSales.dto;

public record ShoppingCartCarsDTO(
    Long id,
    Long carId,
    int quantity
) {
}
