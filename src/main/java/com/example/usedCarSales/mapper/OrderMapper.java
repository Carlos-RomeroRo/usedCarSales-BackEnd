package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usedCarSales.dto.OrderDTO;
import com.example.usedCarSales.entity.Order;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "idOrder", target = "orderId")
    @Mapping(source = "user.idUser", target = "userId")
    @Mapping(source = "car.idCar", target = "carId")
    @Mapping(source = "payment.idPayment", target = "paymentId")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "orderId", target = "idOrder")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "payment", ignore = true)
    Order orderDTOtoOrder(OrderDTO orderDTO);
}
