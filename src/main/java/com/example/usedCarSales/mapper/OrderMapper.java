package com.example.usedCarSales.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.usedCarSales.dto.OrderDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.entity.Order;
import com.example.usedCarSales.entity.Payment;
import com.example.usedCarSales.entity.User;
import com.example.usedCarSales.repository.CarRepository;
import com.example.usedCarSales.repository.PaymentRepository;
import com.example.usedCarSales.repository.UserRepository;


public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "idOrder", target = "orderId")
    @Mapping(source = "user.idUser", target = "userId")
    @Mapping(source = "car.idCar", target = "carId")
    @Mapping(source = "payment.idPayment", target = "paymentId")
    OrderDTO OrderToOrderDTO(Order order);

    default Order OrderDtotoOrder(OrderDTO orderDTO, UserRepository userRepository, CarRepository carRepository, PaymentRepository paymentRepository){
        if (orderDTO == null) {
            return null;
        }
        Order order = new Order();
        order.setIdOrder(orderDTO.orderId());
        order.setDateOrder(orderDTO.dateOrder());
        order.setTotalPrice(orderDTO.totalPrice());
        if( orderDTO.userId() != null) {
            User user = userRepository.findById(orderDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + orderDTO.userId()));
            order.setUser(user);
        }
        if (orderDTO.carId() != null) {
            Car car = carRepository.findById(orderDTO.carId())
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + orderDTO.carId()));
            order.setCar(car);
        }
        if (orderDTO.paymentId() != null) {
            Payment payment = paymentRepository.findById(orderDTO.paymentId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment not found with id: " + orderDTO.paymentId()));
            order.setPayment(payment);
        }
        return order;
    }


}
