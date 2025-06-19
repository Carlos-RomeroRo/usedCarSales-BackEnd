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

    @Mapping(source = "shoppingCartId", target = "idShoppingCart")
    @Mapping(target = "user", ignore = true)
    ShoppingCart shoppingCartDTOToShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
