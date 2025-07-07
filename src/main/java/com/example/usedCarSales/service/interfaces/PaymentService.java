package com.example.usedCarSales.service.interfaces;

import java.util.List;

import com.example.usedCarSales.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePaymentDTO(Long id, PaymentDTO paymentDTO);
    Boolean deletePayment(Long id);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
}
