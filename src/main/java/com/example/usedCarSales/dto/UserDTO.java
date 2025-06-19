package com.example.usedCarSales.dto;

import java.util.List;

public record UserDTO(
    Long userId,
    String name,
    String email,
    String password,
    List<Long> ordersIds,
    List<Long> carsIds,
    Long shoppingCartId,
    Long roleId
) {
}
