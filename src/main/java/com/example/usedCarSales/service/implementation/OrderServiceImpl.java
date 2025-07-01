package com.example.usedCarSales.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usedCarSales.dto.OrderDTO;
import com.example.usedCarSales.entity.Car;
import com.example.usedCarSales.entity.Order;
import com.example.usedCarSales.entity.Payment;
import com.example.usedCarSales.entity.User;
import com.example.usedCarSales.mapper.OrderMapper;
import com.example.usedCarSales.repository.CarRepository;
import com.example.usedCarSales.repository.OrderRepository;
import com.example.usedCarSales.repository.PaymentRepository;
import com.example.usedCarSales.repository.UserRepository;
import com.example.usedCarSales.service.interfaces.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired 
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if(orderRepository.existsById(orderDTO.orderId())) {
            log.error("Order with ID {} already exists", orderDTO.orderId());
            throw new IllegalArgumentException("Order with this ID already exists");
        }
        try{
            //1. get user from repository
            User user = userRepository.findById(orderDTO.userId()).orElseThrow(()-> new IllegalArgumentException("User not found"));
            //2. get car from repository
            Car car = carRepository.findById(orderDTO.carId()).orElseThrow(()-> new IllegalArgumentException("Car not found"));
            // 3. get payment from repository
            Payment payment = paymentRepository.findById(orderDTO.paymentId()).orElseThrow(()-> new IllegalArgumentException("Payment not found"));
            //4. Convert DTO to entity
            Order newOrder = orderMapper.orderDTOtoOrder(orderDTO);
            newOrder.setUser(user); // Assign user manually
            newOrder.setCar(car); // Assign car manually
            newOrder.setPayment(payment); // Assign payment manually
            //5. Save the order
            Order savedOrder = orderRepository.save(newOrder);
            log.info("Order with ID {} created successfully", savedOrder.getIdOrder());
            return orderMapper.orderToOrderDTO(savedOrder);
        }catch(Exception e) {
            log.error("Error creating order", e);
            throw new RuntimeException("Error creating order", e);
        }

    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO, Long id) {
        try{
            Order existingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            // Update the fields of the existing order with the new values
            existingOrder.setDateOrder(orderDTO.dateOrder());
            existingOrder.setTotalPrice(orderDTO.totalPrice());
            if(orderDTO.userId() != null) {
                User user = userRepository.findById(orderDTO.userId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                existingOrder.setUser(user);
            }
            if(orderDTO.carId() != null) {
                Car car = carRepository.findById(orderDTO.carId())
                        .orElseThrow(() -> new IllegalArgumentException("Car not found"));
                existingOrder.setCar(car);
            }
            if (orderDTO.paymentId() != null) {
                Payment payment = paymentRepository.findById(orderDTO.paymentId())
                        .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
                payment.setOrder(existingOrder); 
                paymentRepository.save(payment); 
            }
            // Save the updated order
            Order updatedOrder = orderRepository.save(existingOrder);
            // Log the successful update
            log.info("Order with ID {} updated successfully", updatedOrder.getIdOrder());
            return orderMapper.orderToOrderDTO(updatedOrder);
        }catch(Exception e) {
            log.error("Error updating order", e);
            throw new RuntimeException("Error updating order", e);
        }

    }

    @Override
    @Transactional
    public Boolean deleteOrder(Long id) {
        try {
            if(orderRepository.findById(id).isPresent()) {
                orderRepository.deleteById(id);
                return true;
            }else{
                log.error("Order with ID {} does not exist", id);
                throw new IllegalArgumentException("Order with this ID does not exist");
            }
        }catch(Exception e) {
            log.error("Error deleting order", e);
            throw new RuntimeException("Error deleting order", e);
        }
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        try {
            Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            log.info("Order with ID {} retrieved successfully", id);
            return orderMapper.orderToOrderDTO(order);
        }catch(Exception e) {
            log.error("Error getting order", e);
            throw new RuntimeException("Error getting order", e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            log.info("Retrieved {} orders from the database", orders.size());
            return orders.stream()
                    .map(orderMapper::orderToOrderDTO)
                    .toList();
        } catch (Exception e) {
            log.error("Error retrieving all orders: {}", e.getMessage());
            throw new RuntimeException("Error retrieving all orders", e);
        }
    }   
}
