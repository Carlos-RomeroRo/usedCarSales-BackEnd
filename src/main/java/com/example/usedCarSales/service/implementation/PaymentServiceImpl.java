package com.example.usedCarSales.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usedCarSales.dto.PaymentDTO;
import com.example.usedCarSales.entity.Order;
import com.example.usedCarSales.entity.Payment;
import com.example.usedCarSales.mapper.PaymentMapper;
import com.example.usedCarSales.repository.OrderRepository;
import com.example.usedCarSales.repository.PaymentRepository;
import com.example.usedCarSales.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        if (paymentRepository.existsById(paymentDTO.paymentId())) {
            log.error("Payment with ID {} already exists", paymentDTO.paymentId());
            throw new IllegalArgumentException("Payment with this ID already exists");
        }
        try {
            // 1. Get order form repository
            Order order = orderRepository.findById(paymentDTO.orderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            // 2. convert DTO to entity
            Payment newPayment = paymentMapper.paymentDTOToPayment(paymentDTO);
            newPayment.setOrder(order); // Assign Order manually
            // 4. Save the payment
            Payment savedPayment = paymentRepository.save(newPayment);
            log.info("Payment with ID {} created successfully", savedPayment.getIdPayment());
            return paymentMapper.paymentToPaymentDTO(savedPayment);
        } catch (Exception e) {
            log.error("Error creating payment: {}", e.getMessage());
            throw new RuntimeException("Error creating payment");
        }
    }

    @Override
    public PaymentDTO updatePaymentDTO(Long id, PaymentDTO paymentDTO) {
        try {
            Payment paymentExisting = paymentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
            paymentExisting.setAmountPayment(paymentDTO.amountPayment());
            paymentExisting.setDatePayment(paymentDTO.datePayment());
            paymentExisting.setPaymentMethod(paymentDTO.paymentMethod());
            paymentExisting.setPaymentStatus(paymentDTO.paymentStatus());
            if (paymentDTO.orderId() != null) {
                Order order = orderRepository.findById(paymentDTO.orderId())
                        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
                paymentExisting.setOrder(order); // Assign Order manually
            }
            Payment updatedPayment = paymentRepository.save(paymentExisting);
            log.info("Payment with ID {} updated successfully", updatedPayment.getIdPayment());
            // Convert updated entity to DTO
            return paymentMapper.paymentToPaymentDTO(updatedPayment);

        } catch (Exception e) {
            log.error("Error updating payment: {}", e.getMessage());
            throw new RuntimeException("Error updating payment: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deletePayment(Long id) {
        try {
            if (!paymentRepository.existsById(id)) {
                log.error("Payment with ID {} does not exist", id);
                throw new IllegalArgumentException("Payment with this ID does not exist");
            }
            paymentRepository.deleteById(id);
            log.info("Payment with ID {} deleted successfully", id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting payment: {}", e.getMessage());
            throw new RuntimeException("Error deleting payment: " + e.getMessage(), e);
        }
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        try {
            Payment payment = paymentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
            log.info("Payment with ID {} retrieved successfully", id);
            return paymentMapper.paymentToPaymentDTO(payment);
        } catch (Exception e) {
            log.error("Error retrieving payment: {}", e.getMessage());
            throw new RuntimeException("Error retrieving payment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            log.info("Retrieved {} payments from the database", payments.size());
            return payments.stream().map(paymentMapper::paymentToPaymentDTO).toList();
        } catch (Exception e) {
            log.error("Error retrieving payment: {}", e.getMessage());
            throw new RuntimeException("Error retrieving payment: " + e.getMessage(), e);
        }
    }

}
