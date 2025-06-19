package com.example.usedCarSales.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.usedCarSales.dto.UserDTO;
import com.example.usedCarSales.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
   @Mapping(source = "idUser", target = "userId")
   @Mapping(target = "ordersIds", expression = "java(user.getOrders() != null ? user.getOrders().stream().map(o -> o.getIdOrder()).toList() : java.util.Collections.emptyList())")
   @Mapping(target = "carsIds", expression = "java(user.getCars() != null ? user.getCars().stream().map(c -> c.getIdCar()).toList() : java.util.Collections.emptyList())")
   @Mapping(target = "shoppingCartId", expression = "java(user.getShoppingCart() != null ? user.getShoppingCart().getIdShoppingCart() : null)")
   @Mapping(source = "roleUser.idRole", target = "roleId") 
   UserDTO userToUserDTO(User user);

    @Mapping(source = "userId", target = "idUser")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(target = "roleUser", ignore = true) // se asigna en el servicio
    User userDTOToUser(UserDTO userDTO);
}
