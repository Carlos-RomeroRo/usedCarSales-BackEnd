package com.example.usedCarSales.dto;

import java.math.BigDecimal;
import com.example.usedCarSales.entity.status.CarStatus;

public record CarDTO(
     Long id,
     String brand,
     String model,
     int year,
     BigDecimal price,
     int mileage,
     String image,
     CarStatus status
) {   
}
