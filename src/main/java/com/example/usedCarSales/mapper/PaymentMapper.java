package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.usedCarSales.dto.PaymentDTO;
import com.example.usedCarSales.entity.Order;
import com.example.usedCarSales.entity.Payment;
import com.example.usedCarSales.repository.OrderRepository;


@Mapper(componentModel = "spring")
public interface PaymentMapper {
    //PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class); -> It is not needed on line twelve

    @Mapping(source = "idPayment", target = "paymentId")
    @Mapping(source = "order.idOrder", target = "orderId")
    PaymentDTO paymentToPaymentDTO(Payment payment);

    default Payment paymentDTOToPayment(PaymentDTO paymentDTO, OrderRepository orderRepository) {
        if( paymentDTO == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setIdPayment(paymentDTO.paymentId());
        payment.setDatePayment(paymentDTO.datePayment());
        payment.setAmountPayment(paymentDTO.amountPayment());
        payment.setPaymentMethod(paymentDTO.paymentMethod());
        payment.setPaymentStatus(paymentDTO.paymentStatus());
        if(paymentDTO.orderId() != null) {
            Order order = orderRepository.findById(paymentDTO.orderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + paymentDTO.orderId()));
            payment.setOrder(order);
        }
        return payment;
    }

}
