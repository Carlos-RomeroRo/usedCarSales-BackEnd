package com.example.usedCarSales.service.interfaces;

import java.util.List;

import com.example.usedCarSales.dto.ShoppingCartDTO;

public interface ShopppingCartService {
    ShoppingCartDTO createShoppingCart(ShoppingCartDTO shopppingCartDTO);
    ShoppingCartDTO updateShoppingCart(Long id, ShoppingCartDTO shopppingCartDTO);
    boolean deleteShoppingCart(Long id);
    ShoppingCartDTO getShoppingCartById(Long id);
    List<ShoppingCartDTO> getAllShoppingCarts();
}
