package com.example.usedCarSales.service.interfaces;

import java.util.List;

import com.example.usedCarSales.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(OrderDTO orderDTO, Long id);
    Boolean deleteOrder(Long id);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
}
