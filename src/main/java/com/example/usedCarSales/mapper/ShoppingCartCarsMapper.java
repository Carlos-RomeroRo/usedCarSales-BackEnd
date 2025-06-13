package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.ShoppingCartCarsDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.entity.ShoppingCart;
import com.example.usedCarSales.entity.ShoppingCartCars;
import com.example.usedCarSales.repository.CarRepository;
import com.example.usedCarSales.repository.ShoppingCartRepository;

@Mapper(componentModel = "spring")
public interface ShoppingCartCarsMapper {
    @Mapping(source = "idShoppingCartCar", target = "shoppingCartCarId")
    @Mapping(source = "shoppingCart.idShoppingCart", target = "shoppingCartId")
    @Mapping(source = "car.idCar", target = "carId")
    ShoppingCartCarsDTO shoppingCartCarsToShoppingCartCarsDTO(ShoppingCartCars shoppingCartCars);

    default ShoppingCartCars shoppingCartCarsDTOToShoppingCartCars(ShoppingCartCarsDTO shoppingCartCarsDTO,
                                                                   ShoppingCartRepository shoppingCartRepository, CarRepository carRepository) {
        if (shoppingCartCarsDTO == null) {
            return null;
        }
        ShoppingCartCars shoppingCartCars = new ShoppingCartCars();
        shoppingCartCars.setIdShoppingCartCar(shoppingCartCarsDTO.shoppingCartCarId());
        shoppingCartCars.setQuantity(shoppingCartCarsDTO.quantity());
        if (shoppingCartCarsDTO.shoppingCartId() != null) {
           ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartCarsDTO.shoppingCartId())
           .orElseThrow(() -> new IllegalArgumentException("ShoppingCart not found with id: " + shoppingCartCarsDTO.shoppingCartId()));
            shoppingCartCars.setShoppingCart(shoppingCart);
        }
        if (shoppingCartCarsDTO.carId() != null) {
            Car car = carRepository.findById(shoppingCartCarsDTO.carId())
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + shoppingCartCarsDTO.carId()));
            shoppingCartCars.setCar(car);
        }
        return shoppingCartCars;
    }
}
