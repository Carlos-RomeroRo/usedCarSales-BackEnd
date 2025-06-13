package com.example.usedCarSales.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.usedCarSales.dto.UserDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.entity.Order;
import com.example.usedCarSales.entity.ShoppingCart;
import com.example.usedCarSales.entity.User;
import com.example.usedCarSales.repository.CarRepository;
import com.example.usedCarSales.repository.OrderRepository;
import com.example.usedCarSales.repository.ShoppingCartRepository;

@Mapper(componentModel = "spring")
public interface UserMapper {
   @Mapping(source = "idUser", target = "userId")
   @Mapping(target = "ordersIds", expression = "java(user.getOrders() != null ? user.getOrders().stream().map(o -> o.getIdOrder()).toList() : java.util.Collections.emptyList())")
   @Mapping(target = "carsIds", expression = "java(user.getCars() != null ? user.getCars().stream().map(c -> c.getIdCar()).toList() : java.util.Collections.emptyList())")
   @Mapping(target = "shoppingCartId", expression = "java(user.getShoppingCart() != null ? user.getShoppingCart().getIdShoppingCart() : null)")
   UserDTO userToUserDTO(User user);

    default User userDTOToUser(UserDTO userDTO, OrderRepository orderRepository, CarRepository carRepository, ShoppingCartRepository shoppingCartRepository) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setIdUser(userDTO.userId());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        if (userDTO.ordersIds() != null && !userDTO.ordersIds().isEmpty()) {
            List<Order> orders = userDTO.ordersIds().stream()
                .map(id -> orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id)))
                .toList();

            user.setOrders(orders);
        }
        if (userDTO.carsIds() != null && !userDTO.carsIds().isEmpty()) {
            List<Car> cars = userDTO.carsIds().stream()
                .map(id -> carRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id)))
                .toList();
            user.setCars(cars);
        }
        if (userDTO.shoppingCartId() != null) {
            ShoppingCart shoppingCart = shoppingCartRepository.findById(userDTO.shoppingCartId())
                .orElseThrow(() -> new IllegalArgumentException("ShoppingCart not found with id: " + userDTO.shoppingCartId()));
            user.setShoppingCart(shoppingCart);
        }
        return user;
    }
}
