package com.example.usedCarSales.dto;

import java.util.List;

public record UserDTO(
    Long idUser,
    String name,
    String email,
    String password,
    RoleDTO roleUser,
    List<Long> ordersIds,
    List<Long> carsIds,
    Long shoppingCartIds
) {
}
