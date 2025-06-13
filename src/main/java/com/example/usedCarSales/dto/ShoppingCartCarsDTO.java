package com.example.usedCarSales.dto;

public record ShoppingCartCarsDTO(
    Long shoppingCartCarId,
    Long shoppingCartId,
    Long carId,
    int quantity
) {
}
