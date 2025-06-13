package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.ShoppingCartDTO;
import com.example.usedCarSales.entity.ShoppingCart;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {
    
    @Mapping(source = "idShoppingCart", target = "shoppingCartId")
    @Mapping(source = "user.idUser", target = "userId")
    ShoppingCartDTO shoppingCartToShoppingCartDTO(ShoppingCart shoppingCart);

    default ShoppingCart shoppingCartDTOToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        if (shoppingCartDTO == null) {
            return null;
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setIdShoppingCart(shoppingCartDTO.shoppingCartId());
        return shoppingCart;
    }
}
