package com.example.usedCarSales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.usedCarSales.dto.PaymentDTO;
import com.example.usedCarSales.entity.Payment;



@Mapper(componentModel = "spring")
public interface PaymentMapper {
    //PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class); -> It is not needed on line twelve

    @Mapping(source = "idPayment", target = "paymentId")
    @Mapping(source = "order.idOrder", target = "orderId")
    PaymentDTO paymentToPaymentDTO(Payment payment);

    @Mapping(source = "paymentId", target = "idPayment")
    @Mapping(target = "order", ignore = true)
    Payment paymentDTOToPayment(PaymentDTO paymentDTO);

}
