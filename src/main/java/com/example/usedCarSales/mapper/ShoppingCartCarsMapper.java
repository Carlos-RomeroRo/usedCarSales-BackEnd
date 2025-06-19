package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.ShoppingCartCarsDTO;
import com.example.usedCarSales.entity.ShoppingCartCars;


@Mapper(componentModel = "spring")
public interface ShoppingCartCarsMapper {
    @Mapping(source = "idShoppingCartCar", target = "shoppingCartCarId")
    @Mapping(source = "shoppingCart.idShoppingCart", target = "shoppingCartId")
    @Mapping(source = "car.idCar", target = "carId")
    ShoppingCartCarsDTO shoppingCartCarsToShoppingCartCarsDTO(ShoppingCartCars shoppingCartCars);

    @Mapping(source = "shoppingCartCarId", target = "idShoppingCartCar")
    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(target = "car", ignore = true)
    ShoppingCartCars shoppingCartCarsDTOToShoppingCartCars(
        ShoppingCartCarsDTO shoppingCartCarsDTO);    

}
